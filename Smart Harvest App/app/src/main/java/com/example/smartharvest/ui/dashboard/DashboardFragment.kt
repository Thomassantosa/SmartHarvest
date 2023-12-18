package com.example.smartharvest.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartharvest.databinding.FragmentDashboardCustomerBinding
import com.example.smartharvest.databinding.FragmentDashboardNoncustomerBinding
import com.example.smartharvest.helper.ViewModelFactory

class DashboardFragment : Fragment() {

    private lateinit var root: View
    private var _binding: FragmentDashboardCustomerBinding? = null
    private var _binding2: FragmentDashboardNoncustomerBinding? = null
    private lateinit var dashboardViewModel: DashboardViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val binding2 get() = _binding2!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity().applicationContext)
        )[DashboardViewModel::class.java]

        var type = dashboardViewModel.getUser().value?.type
        dashboardViewModel.getUser().observe(viewLifecycleOwner) {user ->
            if (!user.type.equals("Customer")) {
                Log.d("TEST", "TEST A")
                _binding = FragmentDashboardCustomerBinding.inflate(inflater, container, false)
                root = binding.root
                type = "Not Customer"
            } else {
                Log.d("TEST", "TEST B")
                _binding2 = FragmentDashboardNoncustomerBinding.inflate(inflater, container, false)
                root = binding2.root
                type = "Customer"
            }
        }

        type?.let { Log.d("TEST", it) }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _binding2 = null
    }
}