package com.example.smartharvest.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartharvest.adapter.ProductCatalogListAdapter
import com.example.smartharvest.data.responses.ProductcatalogItem
import com.example.smartharvest.databinding.FragmentHomeBinding
import com.example.smartharvest.helper.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: ProductCatalogListAdapter
    private lateinit var homeViewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val layoutManager = LinearLayoutManager(root.context)
//        val layoutManager = LinearLayoutManager(this)
        binding.productCatalogRecyclerview.layoutManager = layoutManager

        setupViewModel()
        setupAction()

        return root
    }

    private fun setupViewModel() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        homeViewModel.listProductCatalog.observe(viewLifecycleOwner) { productsCatalog ->
            setProductCatalogData(productsCatalog.productcatalog)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setProductCatalogData(productsCatalog: List<ProductcatalogItem>) {
        binding.productCatalogRecyclerview.adapter = ProductCatalogListAdapter(productsCatalog)
    }

    private fun setupAction() {
//        binding.postingPageButton.setOnClickListener {
//            val intent = Intent(this, AddStoryActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}