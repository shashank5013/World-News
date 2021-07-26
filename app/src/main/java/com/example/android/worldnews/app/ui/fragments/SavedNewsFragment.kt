package com.example.android.worldnews.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.worldnews.app.adapters.NewsAdapter
import com.example.android.worldnews.app.viewmodels.NewsViewModel
import com.example.android.worldnews.databinding.FragmentSavedNewsBinding
import com.google.android.material.snackbar.Snackbar


class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSavedNewsBinding?=null
    private val binding get()= _binding!!
    private lateinit var mNewsViewModel: NewsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        _binding=FragmentSavedNewsBinding.inflate(inflater, container, false)
        mNewsViewModel=ViewModelProvider(this).get(NewsViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter=NewsAdapter(requireContext())
        adapter.setOnClickListener {
            val action = SavedNewsFragmentDirections.actionSavedNewsFragmentToArticleFragment(it)
            findNavController().navigate(action)
        }

        binding.savedNewsRV.apply {
            this.adapter=adapter
            layoutManager=LinearLayoutManager(requireContext())
        }

        val itemTouchHelperCallback=object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true;
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val article=adapter.differ.currentList[position]
                mNewsViewModel.deleteArticle(article)
                Snackbar.make(view,"Successfully deleted article",Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        mNewsViewModel.addOrUpdateArticle(article)
                    }
                }.show()
            }

        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.savedNewsRV)
        }
        mNewsViewModel.getArticlesFromDatabase().observe(viewLifecycleOwner,{
            adapter.differ.submitList(it)
        })
    }
}