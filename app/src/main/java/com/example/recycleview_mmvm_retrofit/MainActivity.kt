package com.example.recycleview_mmvm_retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.recycleview_mmvm_retrofit.databinding.ActivityMainBinding
import com.example.recycleview_mmvm_retrofit.model.MainRepository
import com.example.recycleview_mmvm_retrofit.model.MyViewModelFactory
import com.example.recycleview_mmvm_retrofit.model.RetrofitService

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        binding.recyclerview.adapter = adapter
        viewModel.movieList.observe(this) {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it)
        }
        viewModel.errorMessage.observe(this) {
        }
        viewModel.getAllMovies()
    }
}