package com.example.testtask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class ViewModel : ViewModel() {
    private val items: MutableLiveData<List<CellData>> = MutableLiveData()
    private val deletedItems: MutableList<CellData> = mutableListOf()

    init {
        fillItems()
    }

    fun getItems(): MutableLiveData<List<CellData>> = items

    //TODO: checking for empty
    fun deleteItem(pos: Int) {
        val newItems: MutableList<CellData> = mutableListOf()
        items.value?.let { newItems.addAll(it) }
        deletedItems.add(newItems.removeAt(pos))
        items.value = newItems
    }

    fun addNewItem() {
        if (addDeletedItem()) return

        val newItems: MutableList<CellData> = mutableListOf()
        items.value?.let {
            newItems.addAll(it)
            newItems.addAtRandom(CellData(it.size + 1))
            items.value = newItems
        }
    }

    private fun addDeletedItem(): Boolean {
        if (deletedItems.isNotEmpty()) {
            val newItems: MutableList<CellData> = mutableListOf()
            items.value?.let {
                newItems.addAll(it)
                newItems.addAtRandom(deletedItems.removeLast())
                items.value = newItems
                return true
            }
        }
        return false
    }

    private fun fillItems() {
        items.value = mutableListOf(
            CellData(1),
            CellData(2),
            CellData(3),
            CellData(4),
            CellData(5),
            CellData(6),
            CellData(7),
            CellData(8),
            CellData(9),
            CellData(10),
            CellData(11),
            CellData(12),
            CellData(13),
            CellData(14),
            CellData(15),
        )
    }
    private fun <V> MutableList<V>.addAtRandom(item: V) {
        this.add(Random().nextInt(this.size), item)
    }
}