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
import com.example.nasabucket.databinding.FragmentHomeBinding
import com.example.nasabucket.viewmodels.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: RoverPhotosAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.viewModelMarsRover = homeViewModel

        // set the adapter
        val adapter = RoverPhotosAdapter()
        binding.list.adapter = adapter


        // Set the viewmodel
        homeViewModel.roverImagesList.observe(this, Observer {
            it?.let {
                adapter.data = it
                adapter.notifyDataSetChanged()
            }
        })


        return root
    }



}