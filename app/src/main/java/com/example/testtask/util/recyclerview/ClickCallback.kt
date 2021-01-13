package com.example.testtask.util.recyclerview

import android.view.View
import android.widget.Button

/**
 * interface that allows you to get a callback when you click on delete
 */
interface ClickCallback {
    fun click(view: View, pos: Int)
}