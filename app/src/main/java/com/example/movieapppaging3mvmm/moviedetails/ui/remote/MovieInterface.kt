package com.example.movieapppaging3mvmm.moviedetails.ui.remote

import com.example.movieapppaging3mvmm.moviedetails.ui.data.MovieResponse
import com.example.movieapppaging3mvmm.moviedetails.ui.data.moviedetail.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("/")
    suspend fun getAllMovies(
        @Query("s")s:String,
        @Query("page")page:Int,
        @Query("apiKey")apiKey:String
    ):Response<MovieResponse>

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i")imdbId:String,
        @Query("apiKey")apiKey:String
    ):Response<MovieDetails>
}