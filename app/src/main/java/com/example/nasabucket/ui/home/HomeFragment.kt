package com.example.nasabucket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.nasabucket.R
import com.example.nasabucket.adapters.RoverPhotosAdapter
import com.example.nasabucket.data.Photos
import com.example.nasabucket.databinding.FragmentHomeBinding
import com.example.nasabucket.viewmodels.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.viewModelMarsRover = homeViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addRoverPhoto.setOnClickListener { homeViewModel.onAddRoverPhotoClicked() }

        // set the adapter
        val adapter = RoverPhotosAdapter()
        binding.list.adapter = adapter

        // Set the viewmodel
        homeViewModel.roverImagesList.observe(this, Observer {
            adapter.data = it
            binding.list.smoothScrollToPosition(adapter.itemCount-1)
        })

    }

}