package com.example.testtask.util.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.data.CellData
import com.example.testtask.databinding.CellBinding

/**
 * class view holder for recycler view item
 * @property layoutInflater - layout inflater
 * @property parent - a special view that can contain other views
 * @property clickCallback - property callback after clicking the delete button
 * @property binding - an instance of a binding class contains direct references to all views that have an ID in the corresponding layout.
 */

class CellHolder(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup,
    private val clickCallback: ClickCallback,
    private val binding: CellBinding =
        CellBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Method that fills cell with data
     * @param cell - data for filling
     */
    fun bind(cell: CellData) {
        binding.cellText.text = cell.number.toString()
        binding.cellButton.setOnClickListener {
            clickCallback.click(it, adapterPosition)
        }
    }
}