package com.example.movieapppaging3mvmm.moviedetails.ui.data

data class MovieResponse(
    val Response: String,
    //hata arıyorum 2 saattir.Değişken isimi de Movie olunca veriler gelmiyorr!!!!
    val Search: List<Movie>,
    val totalResults: String
)