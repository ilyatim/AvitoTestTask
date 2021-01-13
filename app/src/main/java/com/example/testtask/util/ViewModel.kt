package com.example.testtask.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.data.CellData
import kotlinx.coroutines.*
import java.util.*

class ViewModel : ViewModel(), CoroutineScope by MainScope() {

    private val items: MutableLiveData<List<CellData>> = MutableLiveData()
    private val deletedItems: MutableList<CellData> = mutableListOf()

    init {
        fillItems()
        CoroutineScope(coroutineContext).launch {
            delay(5000)
            repeat(1000) {
                addNewItem()
                delay(5000)
            }
        }
    }

    fun getItems(): MutableLiveData<List<CellData>> = items

    //TODO: checking for empty
    fun deleteItem(pos: Int) {
        val newItems: MutableList<CellData> = mutableListOf()
        items.value?.let { newItems.addAll(it) }
        deletedItems.add(newItems.removeAt(pos))
        items.value = newItems
    }

    private fun addNewItem() {
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
        if (this.size == 0) {
            this.add(0, item)
            return
        }
        this.add(Random().nextInt(this.size), item)
    }

    override fun onCleared() {
        super.onCleared()
        if (isActive) cancel()
    }
}