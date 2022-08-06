package com.example.giphyapi.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.giphyapi.databinding.FragmentDetailBinding
import com.example.giphyapi.viewmodel.DetailViewModel
import com.example.giphyapi.viewmodel.DetailViewModelFactory

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        val viewModelFactory = DetailViewModelFactory(
        DetailFragmentArgs.fromBundle(requireArguments()).gif)

        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}