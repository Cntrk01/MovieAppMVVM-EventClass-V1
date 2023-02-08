package com.example.movieapppaging3mvmm.moviedetails.ui.viewwmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.movieapppaging3mvmm.moviedetails.ui.MoviePaging
import com.example.movieapppaging3mvmm.moviedetails.ui.data.moviedetail.MovieDetails
import com.example.movieapppaging3mvmm.moviedetails.ui.details.MovieDetailRepository
import com.example.movieapppaging3mvmm.moviedetails.ui.remote.MovieInterface
import com.example.movieapppaging3mvmm.moviedetails.ui.utils.Events
import com.example.movieapppaging3mvmm.moviedetails.ui.utils.Status
import com.example.movieapppaging3mvmm.moviedetails.ui.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieInterface: MovieInterface
,private val repository: MovieDetailRepository): ViewModel() {

    private val query=MutableLiveData<String>()
    val list=query.switchMap {query->
        Pager(PagingConfig(pageSize = 10)){
            MoviePaging(query,movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s:String){
        query.postValue(s)
    }

    private val _movieDetails=MutableLiveData<Events<Result<MovieDetails>>>()
    val movieDetails:LiveData<Events<Result<MovieDetails>>> = _movieDetails

    fun getMovieDetails(imdb:String){
        viewModelScope.launch {
            _movieDetails.postValue(Events(Result(Status.LOADING,null,null)))
            _movieDetails.postValue(Events(repository.getMovieDetails(imdb)))
        }
    }
}