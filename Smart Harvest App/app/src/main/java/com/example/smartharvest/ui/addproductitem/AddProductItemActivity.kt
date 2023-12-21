package com.example.smartharvest.ui.addproductitem

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.smartharvest.databinding.ActivityAddProductItemBinding
import com.example.smartharvest.databinding.ActivityProductManagementBinding
import com.example.smartharvest.helper.ViewModelFactory

class AddProductItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductItemBinding
    private lateinit var addProductItemViewModel: AddProductItemViewModel
    private lateinit var productCatalogList: MutableMap<String, String>
    private lateinit var spinner: Spinner

    init {
        productCatalogList = mutableMapOf() // Initialize here
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupData()
        setupAction()
    }

    private fun setupData() {
        addProductItemViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[AddProductItemViewModel::class.java]


        addProductItemViewModel.getUser().observe(this) {user ->
            if (user.token.isNotEmpty()) {
                Log.d("TEST", "TEST A")
                addProductItemViewModel.getAllProductCatalog(user.token)
                productCatalogList = addProductItemViewModel.result
                Log.d("TEST OUTPUT", productCatalogList.toString())

                spinner = binding.productCatalogSpinner
                val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, productCatalogList.values.toMutableList())
                Log.d("TEST OUTPUT MUTABLE", productCatalogList.values.toMutableList().toString())
                spinner.adapter = adapter
            }
        }


        addProductItemViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setupAction() {
        binding.submit.setOnClickListener {

            val catalogName = binding.productCatalogSpinner.selectedItem.toString()
            var catalogId: String = ""
            for (current in productCatalogList){
                if (current.value.equals(catalogName)) catalogId = current.key
            }

            val desc = binding.addDesc.text.toString()
            val price = binding.addPrice.text.toString().toInt()
            val harvestPlace = binding.addHarvestPlace.text.toString()

//            val year = binding.addHarvestDate.year
//            val month = binding.addHarvestDate.month + 1
//            val day = binding.addHarvestDate.dayOfMonth
//            val harvestDate = "$year-$month-$day" + "T00:00:00.000Z"
            val harvestDate = "2023-12-20T00:00:00.000Z"

            addProductItemViewModel.getUser().observe(this) {user ->
                if (user.token.isNotEmpty()) {
                    if (user.type.equals("Producer")) {
                        val producerId = user.id
                        val producerName = user.name
                        val status = "In Producer"
                        addProductItemViewModel.uploadItemByProducer(catalogId,
                            desc,price,
                            harvestPlace,
                            harvestDate, producerId, producerName, status)
                    } else if (user.type.equals("Distributor")) {
                        val distributorId = user.id
                        val distributorName = user.name
                        val status = "In Producer"
                        addProductItemViewModel.uploadItemByDistributor(catalogId,
                            desc,price,
                            harvestPlace,
                            harvestDate, distributorId, distributorName, status)
                    } else if (user.type.equals("Seller")) {
                        val sellerId = user.id
                        val sellerName = user.name
                        val status = "In Producer"
                        addProductItemViewModel.uploadItemBySeller(catalogId,
                            desc,price,
                            harvestPlace,
                            harvestDate, sellerId, sellerName, status)
                    }
                }
            }


            addProductItemViewModel.addProductResponse.observe(this) { response ->
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                if(response.message == "Create Product Item Success"){
                    finish()
                }

            }

            addProductItemViewModel.errorResponse.observe(this) { response ->
                if(response.message != "Create Product Item Success"){
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}