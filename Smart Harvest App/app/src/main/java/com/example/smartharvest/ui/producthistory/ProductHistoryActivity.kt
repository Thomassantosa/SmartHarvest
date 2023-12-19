package com.example.smartharvest.ui.producthistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartharvest.R
import com.example.smartharvest.adapter.ProductHistoryListAdapter
import com.example.smartharvest.data.responses.DataItem
import com.example.smartharvest.databinding.ActivityProductHistoryBinding
import com.example.smartharvest.helper.ViewModelFactory

class ProductHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductHistoryBinding
    private lateinit var productHistoryViewModel: ProductHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvProductHistory.layoutManager = layoutManager

        setupViewModel()
    }

    private fun setupViewModel() {
        productHistoryViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[ProductHistoryViewModel::class.java]

        productHistoryViewModel.getUser().observe(this) {user ->
            if (user.token.isNotEmpty()) {
                if (user.type.equals("Producer")) {
                    binding.greeting.text = "Producer : " + user.name
                    productHistoryViewModel.getProductHistoryProducer(user.id)
                } else if (user.type.equals("Distributor")) {
                    binding.greeting.text = "Distributor : " + user.name
                    productHistoryViewModel.getProductHistoryDistributor(user.id)
                } else if (user.type.equals("Seller")) {
                    binding.greeting.text = "Seller : " + user.name
                    productHistoryViewModel.getProductHistorySeller(user.id)
                } else {
                    binding.greeting.text = "Hello " + user.name
                }
            }
        }

        productHistoryViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        productHistoryViewModel.listProductitem.observe(this) { productItems ->
            setProductHistoriesData(productItems.data)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setProductHistoriesData(productItems: List<DataItem>) {
        binding.rvProductHistory.adapter = ProductHistoryListAdapter(productItems)
    }
}