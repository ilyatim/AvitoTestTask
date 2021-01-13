package com.example.testtask.util.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.data.CellData
import com.example.testtask.databinding.CellBinding

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

    fun bind(cell: CellData) {
        binding.cellText.text = cell.number.toString()
        binding.cellButton.setOnClickListener {
            clickCallback.click(it, adapterPosition)
        }
    }
}