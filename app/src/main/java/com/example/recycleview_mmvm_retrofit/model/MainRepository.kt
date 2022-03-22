package com.example.recycleview_mmvm_retrofit.model

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllMovies() = retrofitService.getAllMovies()
}