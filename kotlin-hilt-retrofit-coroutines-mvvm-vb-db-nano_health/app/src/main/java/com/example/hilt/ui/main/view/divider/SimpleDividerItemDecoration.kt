package com.example.hilt.ui.main.view.divider

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hilt.R

class SimpleDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    // Retrieve the divider drawable from resources
    private val mDivider: Drawable = ContextCompat.getDrawable(context, R.drawable.divider)!!

    // Retrieve the horizontal margin between dividers
    private val horizontalMargin: Int = context.resources.getDimensionPixelSize(R.dimen.divider_horizontal_margin)

    // Draw the divider on the canvas
    override fun onDrawOver(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft + horizontalMargin
        val right = parent.width - parent.paddingRight - horizontalMargin
        val childCount = parent.childCount

        // Iterate over each child view and draw the divider
        for (i in 0 until childCount - 1) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }
}
