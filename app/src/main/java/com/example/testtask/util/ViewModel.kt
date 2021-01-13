package com.example.testtask.util

import android.util.Log
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
        CountDownTimer(coroutineContext).start(5000 + System.currentTimeMillis()) {
            addNewItem()
        }
    }

    /**
     * Method that return MutableLiveData object
     */
    fun getItems(): MutableLiveData<List<CellData>> = items

    /**
     * Method that delete item on selected position
     * @param pos - position of the item to delete
     */
    fun deleteItem(pos: Int) {
        val newItems: MutableList<CellData> = mutableListOf()
        items.value?.let {
            newItems.addAll(it)
            deletedItems.add(newItems.removeAt(pos))
            items.value = newItems
        }
    }

    /**
     * Method that add new item in array
     * checks that there are deleted elements,
     * if not, creates a new one with the number size + 1
     */
    private fun addNewItem() {
        if (deletedItems.isNotEmpty()) {
            addAtRandom(deletedItems.removeLast())
        } else {
            addAtRandom(getNewCell())
        }
    }

    /**
     * Method that return new item for array with number - size + 1.
     * @return new item with number - size + 1
     */
    private fun getNewCell(): CellData {
        items.value?.let { return CellData(it.size + 1) }
        return CellData(1)
    }

    /**
     * Method that add item on random position
     * @param cell - new item you want to add
     */
    private fun addAtRandom(cell: CellData) {
        val newItems: MutableList<CellData> = mutableListOf()
        items.value?.let {
            newItems.addAll(it)
            newItems.addAtRandom(cell)
            items.value = newItems
        }
    }

    /**
     * Method that fill primary array with 15 items
     */
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

    /**
     * extension function to add a new element to a random position
     * @param item - new item you want to add
     */
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