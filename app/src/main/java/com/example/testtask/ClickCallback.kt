package com.example.testtask

import android.view.View
import android.widget.Button

interface ClickCallback {
    fun click(view: View, pos: Int)
}