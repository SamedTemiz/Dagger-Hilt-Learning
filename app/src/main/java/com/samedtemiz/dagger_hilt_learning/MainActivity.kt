package com.samedtemiz.dagger_hilt_learning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postAdapter = PostAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_posts)
        recyclerView.adapter = postAdapter

        viewModel.getLiveDataObserver().observe(this, object : Observer<List<Post>>{
            override fun onChanged(value: List<Post>) {
                if(value != null){
                    postAdapter.setList(value)
                    postAdapter.notifyDataSetChanged()
                }
            }

        })

        viewModel.loadData()
    }
}