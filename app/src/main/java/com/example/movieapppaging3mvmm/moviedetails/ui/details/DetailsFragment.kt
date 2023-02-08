package com.example.movieapppaging3mvmm.moviedetails.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieapppaging3mvmm.R
import com.example.movieapppaging3mvmm.databinding.FragmentDetailsBinding
import com.example.movieapppaging3mvmm.databinding.FragmentMovieBinding
import com.example.movieapppaging3mvmm.moviedetails.ui.utils.Status
import com.example.movieapppaging3mvmm.moviedetails.ui.viewwmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding:FragmentDetailsBinding ?=null
    private val binding get() = _binding!!

    val viewModel:MovieViewModel by viewModels()

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovieDetails(args.imdbID)

        binding.backPress.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.movieDetails.observe(viewLifecycleOwner){
            //it bize Events<Result<MovieDetails>>! çeviriyor
            when(it.getContentIfNotHandled()?.status){
                Status.SUCCESS->{
                    binding.detailsProgress.visibility=View.GONE
                    //Databinding aralığıyla verileri xmle verdik!
                    //it bize Events<Result<MovieDetails>>! çeviriyor
                    binding.movieDetails=it.peekContent().data
                }
                Status.ERROR->{
                    binding.detailsProgress.visibility=View.GONE
                }
                Status.LOADING->{  binding.detailsProgress.visibility=View.VISIBLE}
                else -> {}
            }
        }
    }
}