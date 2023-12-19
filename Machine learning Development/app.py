import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'
from flask import Flask, request, jsonify
import tensorflow as tf
#from tensorflow.keras.models import load_model
import numpy as np

app = Flask(__name__)
model = tf.keras.models.load_model('smartharvest2.h5')

# Assuming you know the number of features in your input data
num_features = 10  # Replace with the actual number of features

@app.route('/forecast', methods=['GET'])
def forecast():
    # Get parameters from the query string or request headers
    # Example: http://127.0.0.1:5000/forecast?input_sequence=1,2,3,4,5
    input_sequence = request.args.get('input_sequence')

    # Check if 'input_sequence' parameter is provided
    if input_sequence is None:
        return jsonify({'error': 'Missing input_sequence parameter in the request'}), 400

    # Convert input_sequence to a list of floats
    input_sequence = list(map(float, input_sequence.split(',')))

    # Reshape the input sequence for the model
    input_sequence = np.array(input_sequence).reshape(1, -1, num_features)

    # Make time-series predictions using your loaded model
    forecast = model.predict(input_sequence)[0].tolist()

    return jsonify({'forecast': forecast})

if __name__ == '__main__':
    app.run(debug=True)