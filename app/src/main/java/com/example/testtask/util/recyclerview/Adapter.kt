package com.example.testtask.util.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.testtask.data.CellData

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