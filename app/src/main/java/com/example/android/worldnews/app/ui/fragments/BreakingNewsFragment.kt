package com.example.android.worldnews.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.worldnews.app.adapters.NewsAdapter
import com.example.android.worldnews.app.viewmodels.NewsViewModel
import com.example.android.worldnews.databinding.FragmentBreakingNewsBinding


class BreakingNewsFragment : Fragment() {

    private var _binding: FragmentBreakingNewsBinding?=null
    private val binding get()= _binding!!
    private lateinit var mNewsViewModel:NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentBreakingNewsBinding.inflate(inflater, container, false)
        mNewsViewModel=ViewModelProvider(this).get(NewsViewModel::class.java)
        val adapter=NewsAdapter(requireContext())
        adapter.setOnClickListener {
            val action=BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(it)
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            layoutManager=LinearLayoutManager(requireContext())
            this.adapter=adapter
        }

        mNewsViewModel.getBreakingNews()
        mNewsViewModel.dataFromInternet.observe(viewLifecycleOwner, {
            adapter.differ.submitList(it.articles)
        })
        return binding.root
    }
}