package com.example.android.worldnews.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.worldnews.app.viewmodels.NewsViewModel
import com.example.android.worldnews.databinding.FragmentArticleBinding
import com.google.android.material.snackbar.Snackbar


class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding?=null
    private val binding get()= _binding!!
    private lateinit var mNewsViewModel: NewsViewModel

    private val args by navArgs<ArticleFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.webView.apply {
            webViewClient= WebViewClient()
            loadUrl(args.article.url)
        }

        mNewsViewModel=ViewModelProvider(this).get(NewsViewModel::class.java)

        binding.floatingActionButton.setOnClickListener {
            mNewsViewModel.addOrUpdateArticle(args.article)
            Snackbar.make(view,"Article Saved Successfully",Snackbar.LENGTH_SHORT).show()
        }
    }
}