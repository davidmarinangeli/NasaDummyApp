package com.example.nasabucket.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.nasabucket.R
import com.example.nasabucket.databinding.FragmentDashboardBinding
import com.example.nasabucket.viewmodels.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {


    private val dashboardViewModel: DashboardViewModel by lazy {
        ViewModelProviders.of(this).get(DashboardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val image = root.findViewById<ImageView>(R.id.apod_image)

        val binding = FragmentDashboardBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the viewmodel
        binding.viewModelAPOD = dashboardViewModel

        dashboardViewModel.apod.observe(this, Observer {
            apod_title.text = it.title
            apod_date.text = it.date
            apod_explanation.text = it.explanation

            if (image != null) {
                Glide.with(this).load(it.url).into(image)
            }

        })

        return root
    }
}