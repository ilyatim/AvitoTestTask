package com.example.testtask.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.example.testtask.util.CountDownTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.coroutineContext

/**
 * Activity class
 * @property viewModel - ViewModel instance that is responsible for preparing and managing the data for an Activity
 * @property adapter - provides access to the data items
 * @property recyclerView
 * @property binding - an instance of a binding class contains direct references to all views that have an ID in the corresponding layout.
 * @property clickCallback - object that allows you to get a callback when you click on delete
 */
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

    /**
     * Method that set layout manager depending on the device configuration
     */
    private fun setLayoutManager() {
        when (this.resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                binding.recyclerView.layoutManager = GridLayoutManager(this, 4)
            }
            else -> {
                binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
            }
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