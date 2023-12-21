package com.example.smartharvest.ui.productactivedetail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.smartharvest.data.responses.DataItem
import com.example.smartharvest.databinding.ActivityProductActiveDetailBinding
import com.example.smartharvest.helper.ViewModelFactory
import com.example.smartharvest.ui.productmanagement.ProductManagementViewModel
import com.example.smartharvest.ui.showqr.ShowQrActivity
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ProductActiveDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductActiveDetailBinding
    private lateinit var productActiveDetailViewModel: ProductActiveDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductActiveDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupData()
        setupAction()
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

        productActiveDetailViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[ProductActiveDetailViewModel::class.java]

        productActiveDetailViewModel.getUser().observe(this) {user ->
            if (user.token.isNotEmpty()) {
                if (user.type.equals("Producer")) {

                    if (productItem.priceProducer.toString() == "null") {
                        binding.apply {
                            textItemPrice.text = "No price data"
                        }
                    } else {
                        val formattedItemPrice = numberFormat.format(productItem.priceProducer)
                        binding.apply {
                            textItemPrice.text = "IDR " + formattedItemPrice.toString()
                        }
                    }

                    binding.textUserPrice.text = "(producer price)"
                } else if (user.type.equals("Distributor")) {

                    if (productItem.priceDistributor.toString() == "null") {
                        binding.apply {
                            textItemPrice.text = "No price data"
                        }
                    } else {
                        val formattedItemPrice = numberFormat.format(productItem.priceDistributor)
                        binding.apply {
                            textItemPrice.text = "IDR " + formattedItemPrice.toString()
                        }
                    }

                    binding.textUserPrice.text = "(distributor price)"
                } else if (user.type.equals("Seller")) {

                    if (productItem.priceSeller.toString() == "null") {
                        binding.apply {
                            textItemPrice.text = "No price data"
                        }
                    } else {
                        val formattedItemPrice = numberFormat.format(productItem.priceSeller)
                        binding.apply {
                            textItemPrice.text = "IDR " + formattedItemPrice.toString()
                        }
                    }

                    binding.textUserPrice.text = "(seller price)"
                } else {
                    binding.textItemPrice.text = "No price data"
                    binding.textUserPrice.text = "(seller price)"
                }
            }
        }

        val formattedNationalPrice = numberFormat.format(productItem.nationalPrice)
        val formattedPredictionPrice = numberFormat.format(productItem.predictionPrice)

        // Date format
        if (productItem.harvestDate.toString().equals(null)) {
            binding.apply {
                textHarvestDate.text = "No data"
            }
        } else {
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
                textHarvestDate.text = formattedDateString
            }
        }


        binding.apply {
            textItemName.text = productItem.name
            textNationalPrice.text = "IDR " + formattedNationalPrice.toString()
            textPredictionPrice.text = "IDR " + formattedPredictionPrice.toString()
            textProducer.text = productItem.producerName
            textDistributor.text = productItem.distributorName
            textSeller.text = productItem.sellerName
            textDescValue.text = productItem.description
        }
    }

    private fun setupAction() {
        binding.btnQr.setOnClickListener {
            val productItem = intent.getParcelableExtra<DataItem>("ProductItem") as DataItem
            val intent = Intent(this@ProductActiveDetailActivity, ShowQrActivity::class.java)
            intent.putExtra("ProductItem", productItem)
            startActivity(intent)
        }
        binding.btnEdit.setOnClickListener {
            Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
        }
    }
}