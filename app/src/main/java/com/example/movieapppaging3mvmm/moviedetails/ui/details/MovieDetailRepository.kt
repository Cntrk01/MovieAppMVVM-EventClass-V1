package com.example.movieapppaging3mvmm.moviedetails.ui.details

import com.example.movieapppaging3mvmm.moviedetails.ui.data.moviedetail.MovieDetails
import com.example.movieapppaging3mvmm.moviedetails.ui.remote.MovieInterface
import com.example.movieapppaging3mvmm.moviedetails.ui.utils.Constants
import com.example.movieapppaging3mvmm.moviedetails.ui.utils.Status
import com.example.movieapppaging3mvmm.moviedetails.ui.utils.Result

class MovieDetailRepository(private val movieInterface: MovieInterface) {

    suspend fun getMovieDetails(imdbId:String):Result<MovieDetails>{
        return try {
            val  response=movieInterface.getMovieDetails(imdbId,Constants.API_KEY)
            if(response.isSuccessful){
                Result(Status.SUCCESS, response.body(), null)
            }else{
                Result(Status.ERROR, null, null)
            }
        }catch (e:java.lang.Exception){
            Result(Status.ERROR, null, null)
        }
    }
}