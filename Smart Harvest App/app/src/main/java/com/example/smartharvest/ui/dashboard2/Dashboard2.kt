package com.example.smartharvest.ui.dashboard2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartharvest.R

class Dashboard2 : Fragment() {

    companion object {
        fun newInstance() = Dashboard2()
    }

    private lateinit var viewModel: Dashboard2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Dashboard2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}