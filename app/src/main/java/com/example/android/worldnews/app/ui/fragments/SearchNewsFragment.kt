package com.example.android.worldnews.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.worldnews.app.adapters.NewsAdapter
import com.example.android.worldnews.app.viewmodels.NewsViewModel
import com.example.android.worldnews.databinding.FragmentSearchNewsBinding
import kotlinx.coroutines.*


class SearchNewsFragment : Fragment() {

    private var _binding: FragmentSearchNewsBinding?=null
    private val binding get()= _binding!!
    private lateinit var mNewsViewModel: NewsViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentSearchNewsBinding.inflate(inflater, container, false)
        val adapter=NewsAdapter(requireContext())
        adapter.setOnClickListener {
            val action=SearchNewsFragmentDirections.actionSearchNewsFragmentToArticleFragment(it)
            findNavController().navigate(action)
        }
        
        binding.searchRecyclerView.apply { 
            this.adapter=adapter
            layoutManager=LinearLayoutManager(requireContext())
        }
        mNewsViewModel=ViewModelProvider(this).get(NewsViewModel::class.java)
        
        mNewsViewModel.dataFromInternet.observe(viewLifecycleOwner, {
            adapter.differ.submitList(it.articles)
        })

        var job: Job?=null
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               if(!query.isNullOrEmpty()){
                   mNewsViewModel.searchNews(query)
               }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                job?.cancel()
                job= CoroutineScope(Dispatchers.IO).launch {
                    delay(500L)
                    if(!newText.isNullOrEmpty()){
                        mNewsViewModel.searchNews(newText)
                    }
                }
                return false
            }

        })
        return binding.root
    }
}