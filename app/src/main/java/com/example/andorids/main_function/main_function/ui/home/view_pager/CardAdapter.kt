package com.example.andorids.main_function.main_function.ui.home.view_pager

import androidx.cardview.widget.CardView

interface CardAdapter {
    fun getBaseElevation(): Float
    fun getCardViewAt(position: Int): CardView
    fun getCount(): Int

    companion object {
        const val MAX_ELEVATION_FACTOR = 8
    }
}