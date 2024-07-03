package com.example.mukproj.presentation

import com.example.mukproj.databinding.ActivityMainBinding


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mukproj.presentation.adapter.CarouselAdapter
import com.example.mukproj.presentation.adapter.ListAdapter
import com.example.mukproj.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var listAdapter: ListAdapter
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the ViewPager2 for the image carousel
        carouselAdapter = CarouselAdapter(emptyList())
        binding.viewPager.adapter = carouselAdapter

        // Set up the RecyclerView for the list
        listAdapter = ListAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = listAdapter


        // Observe the items LiveData
        viewModel.itemsLiveData.observe(this, Observer { items ->
            listAdapter.updateItems(items.map { it.name })
            carouselAdapter.updateImages(items)

        })


        // Set up the search functionality
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                listAdapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}


