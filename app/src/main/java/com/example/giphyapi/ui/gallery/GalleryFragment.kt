package com.example.giphyapi.ui.gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.giphyapi.R
import com.example.giphyapi.adapter.GiphyAdapter
import com.example.giphyapi.databinding.FragmentGalleryBinding
import com.example.giphyapi.ulils.AppApplication
import com.example.giphyapi.viewmodel.GalleryViewModel
import com.example.giphyapi.viewmodel.GalleryViewModelFactory

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding

    private  val viewModel : GalleryViewModel by viewModels {
        GalleryViewModelFactory((requireActivity().application as AppApplication))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = GiphyAdapter(GiphyAdapter.GifClickListener { gif ->
            viewModel.gifDetails(gif)
        })

        binding.recyclerView.adapter = adapter

        setHasOptionsMenu(true)

        viewModel.gifList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
            viewModel.gifSearchComplete()
        }

        viewModel.gifSelected.observe(viewLifecycleOwner) {
            if (it != null) {
               val action = GalleryFragmentDirections.actionGalleryFragmentToDetailFragment(it)
                findNavController()
                    .navigate(action)
                viewModel.gifDetailsComplete()
            }
        }
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_search,menu)

        val searchItem = menu.findItem(R.id.menuSearch)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchGif(a = query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }
}