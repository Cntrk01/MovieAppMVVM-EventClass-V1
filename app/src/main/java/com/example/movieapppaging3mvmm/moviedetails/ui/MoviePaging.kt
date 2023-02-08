package com.example.movieapppaging3mvmm.moviedetails.ui

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapppaging3mvmm.moviedetails.ui.data.Movie
import com.example.movieapppaging3mvmm.moviedetails.ui.data.MovieResponse
import com.example.movieapppaging3mvmm.moviedetails.ui.remote.MovieInterface
import com.example.movieapppaging3mvmm.moviedetails.ui.utils.Constants

class MoviePaging(val s: String, val movieInterface: MovieInterface) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val data = movieInterface.getAllMovies(s, page, Constants.API_KEY)
            Log.d("TAG", "load: ${data.body()}")
            LoadResult.Page(
                data = data.body()?.Search!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.body()?.Search?.isEmpty()!!) null else page + 1
            )


        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }
}