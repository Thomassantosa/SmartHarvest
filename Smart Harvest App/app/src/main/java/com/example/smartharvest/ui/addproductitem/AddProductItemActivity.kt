package com.example.smartharvest.ui.addproductitem

import android.R
import android.app.DatePickerDialog
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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

        spinner = binding.productCatalogSpinner


        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month,
                                                             dayofmonth->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayofmonth)
            updateLable(myCalendar)
        }

        binding.addHarvestDate.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()

        }



        setupData()
        setupAction()
    }

    private fun updateLable(mycalendar: Calendar){
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.addHarvestDate.setText(sdf.format(mycalendar.time))
    }

    private fun setupData() {
        addProductItemViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[AddProductItemViewModel::class.java]


        addProductItemViewModel.getUser().observe(this) {user ->
            if (user.token.isNotEmpty()) {
                addProductItemViewModel.getAllProductCatalog(user.token)
            }
        }

        addProductItemViewModel.listProductCatalog.observe(this) {
            addProductItemViewModel.listProductCatalog.value?.productcatalog?.forEach { data ->
                productCatalogList.put(data.id, data.name) }
            assignData()
        }

        addProductItemViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun assignData() {
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, productCatalogList.values.toMutableList())
        spinner.adapter = adapter
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
            Log.d("TEST ID", catalogId)

            val desc = binding.addDesc.text.toString()
            val price = binding.addPrice.text.toString().toInt()
            val harvestPlace = binding.addHarvestPlace.text.toString()

//            val year = binding.addHarvestDate.year
//            val month = binding.addHarvestDate.month + 1
//            val day = binding.addHarvestDate.dayOfMonth
//            val harvestDate = "$year-$month-$day" + "T00:00:00.000Z"
            val harvestDate = "2023-12-20T00:00:00.000Z" // Masih dummy

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
                        val status = "In Distributor"
                        addProductItemViewModel.uploadItemByDistributor(catalogId,
                            desc,price,
                            harvestPlace,
                            harvestDate, distributorId, distributorName, status)

                    } else if (user.type.equals("Seller")) {
                        val sellerId = user.id
                        val sellerName = user.name
                        val status = "In Seller"
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