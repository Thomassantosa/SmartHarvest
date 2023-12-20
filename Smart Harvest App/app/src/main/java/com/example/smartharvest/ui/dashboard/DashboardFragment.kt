package com.example.smartharvest.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartharvest.databinding.FragmentDashboardBinding
import com.example.smartharvest.helper.ViewModelFactory
import com.example.smartharvest.ui.producthistory.ProductHistoryActivity
import com.example.smartharvest.ui.productmanagement.ProductManagementActivity
import com.example.smartharvest.ui.profile.ProfileActivity

class DashboardFragment : Fragment() {

    private lateinit var root: View
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var dashboardViewModel: DashboardViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity().applicationContext)
        )[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupView()
        setupAction()

        return root
    }

    private fun setupView() {
        dashboardViewModel.getUser().observe(viewLifecycleOwner) {user ->
            if (user.type.equals("Customer")) {
                binding.cardProductOverview.visibility = View.GONE
                binding.cardProductHistory.visibility = View.GONE
            }
        }
    }

    private fun setupAction() {
        binding.cardProfile.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.cardProductOverview.setOnClickListener {
            val intent = Intent(context, ProductManagementActivity::class.java)
            startActivity(intent)
        }
        binding.cardProductHistory.setOnClickListener {
            val intent = Intent(context, ProductHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}