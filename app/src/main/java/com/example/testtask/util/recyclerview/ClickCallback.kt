package com.example.testtask.util.recyclerview

import android.view.View
import android.widget.Button

interface ClickCallback {
    fun click(view: View, pos: Int)
}