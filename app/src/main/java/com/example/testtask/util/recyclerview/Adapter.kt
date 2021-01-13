package com.example.testtask.util.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.testtask.data.CellData

/**
 * Class adapter for recycler view
 * @property inflater - layout inflater
 * @property clickCallback - property callback after clicking the delete button
 * @property diffUtil - is a utility class that can calculate the difference between two lists
 */
class Adapter(
    private val inflater: LayoutInflater,
    private val clickCallback: ClickCallback,
    private val diffUtil: DiffUtil<CellData>
) : ListAdapter<CellData, CellHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellHolder {
        return CellHolder(
            inflater,
            parent,
            clickCallback
        )
    }

    override fun onBindViewHolder(holder: CellHolder, position: Int) {
        holder.bind(getItem(position))
    }
}