package com.example.movieapppaging3mvmm.moviedetails.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapppaging3mvmm.R
import com.example.movieapppaging3mvmm.databinding.FragmentMovieBinding
import com.example.movieapppaging3mvmm.moviedetails.ui.viewwmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieFragment : Fragment() {
    private var _binding:FragmentMovieBinding ?=null
    private val binding get() = _binding!!

    val viewModel : MovieViewModel by viewModels()
    val movieAdapter=MoviePagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setRecyclerView()


        binding.movieSearch.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setQuery(it)
                }
                   return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        movieAdapter.onMovieClick {
            val intent=MovieFragmentDirections.actionMovieFragmentToDetailsFragment(it)
            findNavController().navigate(intent)
        }

        viewModel.list.observe(viewLifecycleOwner, Observer {
            movieAdapter.submitData(lifecycle,it)
        })
    }

    private fun setRecyclerView() {
        binding.movieRecycler.apply {
            adapter=movieAdapter
            layoutManager=GridLayoutManager(requireContext(),2)
        }
    }

}