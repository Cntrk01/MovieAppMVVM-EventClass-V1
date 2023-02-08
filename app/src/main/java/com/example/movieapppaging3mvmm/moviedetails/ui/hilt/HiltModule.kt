package com.example.movieapppaging3mvmm.moviedetails.ui.hilt

import com.example.movieapppaging3mvmm.moviedetails.ui.details.MovieDetailRepository
import com.example.movieapppaging3mvmm.moviedetails.ui.remote.MovieInterface
import com.example.movieapppaging3mvmm.moviedetails.ui.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Singleton
    @Provides
    fun provideRetrofitInterface(): MovieInterface {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(MovieInterface::class.java)
    }
    @Singleton
    @Provides
    fun provideRepository(movieInterface: MovieInterface):MovieDetailRepository{
        return MovieDetailRepository(movieInterface)
    }
}