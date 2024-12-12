package com.example.bankprojectassignments.presentation.util

import android.content.Context
import android.view.View
import android.widget.LinearLayout

class RecyclerViewIndicatorManager(
    private val context: Context,
    private val indicatorLayout: LinearLayout,
    private val selectedDrawableRes: Int,
    private val unSelectedDrawableRes: Int
) {
    private val indicators = mutableListOf<View>()

    fun setupIndicators(itemCount: Int) {
        indicators.clear()
        indicatorLayout.removeAllViews()

        for (i in 0 until itemCount) {
            val dot = View(context).apply {
                layoutParams = LinearLayout.LayoutParams(20, 20).apply {
                    marginStart = 8
                    marginEnd = 8
                }
                setBackgroundResource(unSelectedDrawableRes)
            }
            indicatorLayout.addView(dot)
            indicators.add(dot)
        }

        // Activate the first indicator
        if (indicators.isNotEmpty()) {
            indicators[0].setBackgroundResource(selectedDrawableRes)
        }
    }

    fun updateIndicators(activePosition: Int) {
        for (i in indicators.indices) {
            indicators[i].setBackgroundResource(
                if (i == activePosition) selectedDrawableRes else unSelectedDrawableRes
            )
        }
    }
}

