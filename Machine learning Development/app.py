import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'
from flask import Flask, request, jsonify
import tensorflow as tf
import numpy as np
import pandas as pd
from datetime import datetime, timedelta

app = Flask(__name__)
model = tf.keras.models.load_model('smartharvest2.h5')

# Assuming you know the number of features in your input data
num_features = 15  # Update this to match the expected number of features in your model
sequence_length = 10

# Function to generate timestamps for price predictions
def generate_timestamps(start_date, num_predictions, frequency_minutes=15):
    timestamps = [start_date + timedelta(minutes=i * frequency_minutes) for i in range(num_predictions)]
    return timestamps

# Function to reverse Min-Max scaling
def inverse_min_max_scaling(scaled_value, min_value, max_value):
    return (scaled_value * (max_value - min_value)) + min_value

# Function to format timestamps
def format_timestamps(timestamps):
    return [timestamp.strftime('%Y-%m-%d %H:%M:%S') for timestamp in timestamps]

# Function to format prices as Rupiah
def format_price_as_rupiah(price):
    return f'Rp {int(price):,}'

@app.route('/price_prediction', methods=['GET'])
def price_prediction():
    try:
        # Get parameters from the query string or request headers
        input_sequence = request.args.get('input_sequence')

        if input_sequence is None:
            return jsonify({'error': 'Missing input_sequence parameter in the request'}), 400

        # Correctly split the comma-separated values and convert to float
        input_sequence = list(map(float, input_sequence.split(',')))

        # Ensure the size of the input sequence is compatible with the model's expected input shape
        if len(input_sequence) != num_features * sequence_length:
            return jsonify({'error': 'Invalid size of input_sequence'}), 400

        # Apply Min-Max scaling to the input sequence
        min_val = np.min(input_sequence)
        max_val = np.max(input_sequence)
        scaled_input_sequence = (input_sequence - min_val) / (max_val - min_val)

        # Reshape the scaled input sequence for the model
        scaled_input_sequence = np.array(scaled_input_sequence).reshape(1, sequence_length, num_features)

        # Make time-series predictions using your loaded model
        price_predictions_scaled = model.predict(scaled_input_sequence)[0].tolist()

        # Inverse scaling to get original prices
        price_predictions = [inverse_min_max_scaling(val, min_val, max_val) for val in price_predictions_scaled]

        # Format timestamps to a readable format
        current_timestamp = datetime.now()
        prediction_timestamps = generate_timestamps(current_timestamp, len(price_predictions))

        # Format prices as Rupiah
        price_predictions_rupiah = [format_price_as_rupiah(price) for price in price_predictions]

        # Create a DataFrame with formatted timestamps and price predictions
        prediction_df = pd.DataFrame({'timestamp': format_timestamps(prediction_timestamps), 'price_prediction': price_predictions_rupiah})

        # Convert DataFrame to JSON
        prediction_json = prediction_df.to_json(orient='records')

        return jsonify({'price_predictions': prediction_json})

    except Exception as e:
        # Log the exception or print it for debugging purposes
        print(f"An error occurred: {str(e)}")
        # Return an error response with the actual error message
        return jsonify({'error': f'Internal Server Error: {str(e)}'}), 500

if __name__ == '__main__':
    app.run(debug=True)
