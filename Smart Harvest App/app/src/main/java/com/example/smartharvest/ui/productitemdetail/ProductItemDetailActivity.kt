package com.example.smartharvest.ui.productitemdetail

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.smartharvest.data.responses.DataItem
import com.example.smartharvest.databinding.ActivityProductItemDetailBinding
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ProductItemDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductItemDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupData()
    }

    private fun setupData() {
        val productItem = intent.getParcelableExtra<DataItem>("ProductItem") as DataItem
        if (!productItem.photoUrl.isNullOrEmpty()){
            Glide.with(applicationContext)
                .load(productItem.photoUrl)
                .into(binding.imgProductDetail)
        }

        // Number format
        val indonesianLocale = Locale("in", "ID")
        val numberFormat = NumberFormat.getInstance(indonesianLocale)

        if (productItem.priceSeller.toString() != "null") {
            binding.textUserPrice.text = "(seller price)"
            val formattedItemPrice = numberFormat.format(productItem.priceSeller)
            binding.textItemPrice.text = "IDR " + formattedItemPrice.toString()
        } else if (productItem.priceDistributor.toString() != "null") {
            binding.textUserPrice.text = "(distributor price)"
            val formattedItemPrice = numberFormat.format(productItem.priceDistributor)
            binding.textItemPrice.text = "IDR " + formattedItemPrice.toString()
        } else if (productItem.priceProducer.toString() != "null") {
            binding.textUserPrice.text = "(seller price)"
            val formattedItemPrice = numberFormat.format(productItem.priceProducer)
            binding.textItemPrice.text = "IDR " + formattedItemPrice.toString()
        } else {
            binding.textItemPrice.text = "No price data"
            binding.textUserPrice.text = "(no price data)"
        }

        val formattedNationalPrice = numberFormat.format(productItem.nationalPrice)
        val formattedPredictionPrice = numberFormat.format(productItem.predictionPrice)

        // Date format
        val formattedDateString: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")

            val dateTimeString = productItem.harvestDate
            val parsedHarvestDate = LocalDateTime.parse(dateTimeString, inputFormat)
            formattedDateString = parsedHarvestDate.format(outputFormat)
        } else {
            formattedDateString = productItem.harvestDate.toString()
        }

        binding.apply {
            textItemName.text = productItem.name
            textNationalPrice.text = "IDR " + formattedNationalPrice.toString()
            textPredictionPrice.text = "IDR " + formattedPredictionPrice.toString()
            textProducer.text = productItem.producerName
            textHarvestDate.text = formattedDateString
            textDistributor.text = productItem.distributorName
            textSeller.text = productItem.sellerName
            textDescValue.text = productItem.description
        }
    }
}