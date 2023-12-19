package com.example.smartharvest.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartharvest.databinding.FragmentDashboardCustomerBinding
import com.example.smartharvest.databinding.FragmentDashboardNoncustomerBinding
import com.example.smartharvest.databinding.FragmentHomeBinding
import com.example.smartharvest.helper.ViewModelFactory

class DashboardFragment : Fragment() {

    private lateinit var root: View
    private var _binding: FragmentDashboardCustomerBinding? = null
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

        _binding = FragmentDashboardCustomerBinding.inflate(inflater, container, false)
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
            Toast.makeText(requireActivity(), "Test A", Toast.LENGTH_SHORT).show()
//            val intent = Intent(context, ProfileActivity::class.java)
//            startActivity(intent)
        }
        binding.cardProductOverview.setOnClickListener {
            Toast.makeText(requireActivity(), "Test B", Toast.LENGTH_SHORT).show()
        }
        binding.cardProductHistory.setOnClickListener {
            Toast.makeText(requireActivity(), "Test C", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}