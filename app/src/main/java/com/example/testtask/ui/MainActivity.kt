package com.example.testtask.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.util.recyclerview.Adapter
import com.example.testtask.util.recyclerview.ClickCallback
import com.example.testtask.util.recyclerview.DiffUtil
import com.example.testtask.util.ViewModel
import com.example.testtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModel by viewModels()
    private var adapter: Adapter? = null
    private var recyclerView: RecyclerView? = null

    private lateinit var binding: ActivityMainBinding

    private val clickCallback = object : ClickCallback {
        override fun click(view: View, pos: Int) {
            viewModel.deleteItem(pos)
        }
    }

    private fun setLayoutManager() {
        when (this.resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                binding.recyclerView.layoutManager = GridLayoutManager(this, 4)
            }
            //else -> { throw IllegalArgumentException() }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = Adapter(
            LayoutInflater.from(this),
            clickCallback,
            DiffUtil()
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.recyclerView.adapter = adapter
        setLayoutManager()
        setContentView(binding.root)


        viewModel.getItems().observe(this, Observer {
            adapter?.submitList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
        recyclerView = null
    }
}