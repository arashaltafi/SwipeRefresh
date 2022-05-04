package com.arash.altafi.swiperefresh.kotlin1

import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.swiperefresh.R

class SwipeHelper(
    private val mRecyclerView: RecyclerView,
    private val adapter: ContactAdapter
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    private val context = mRecyclerView.context

    private var callBackground: Drawable? = null
    private var smsBackground: Drawable? = null
    private var callIcon: Drawable? = null
    private var smsIcon: Drawable? = null

    private var iconMargin = 0
    private var initiated = false

    private fun init() {
        val tintFilter = PorterDuffColorFilter(
            ContextCompat.getColor(context, R.color.white),
            PorterDuff.Mode.SRC_ATOP
        )

        callBackground = context.getAttrColor(R.attr.colorPrimaryVariant).toDrawable()
        smsBackground = context.getAttrColor(R.attr.colorPrimaryVariant).toDrawable()

        callIcon = ContextCompat.getDrawable(context, R.drawable.ic_call)
        callIcon?.colorFilter = tintFilter

        smsIcon = ContextCompat.getDrawable(context, R.drawable.ic_message)
        smsIcon?.colorFilter = tintFilter

        iconMargin = 25

        initiated = true

    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
        val swipedPosition = viewHolder.adapterPosition

        when (swipeDir) {
            ItemTouchHelper.RIGHT -> {
                adapter.onCall(context, swipedPosition)
            }
            ItemTouchHelper.LEFT -> {
                adapter.onSms(context, swipedPosition)
            }
        }

        adapter.notifyItemChanged(swipedPosition)
    }


    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        // not sure why, but this method get's called for viewholder that are already swiped away
        if (viewHolder.adapterPosition == -1) {
            return
        }

        if (!initiated) {
            init()
        }

        setupBackground(itemView, dX, c)

        super.onChildDraw(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
    }

    private fun setupBackground(itemView: View, dX: Float, c: Canvas) {
        val itemHeight = itemView.bottom - itemView.top

        when {
            dX < 0 -> {//swipe to left
                smsBackground!!.setBounds(
                    itemView.right + dX.toInt(),
                    itemView.top,
                    itemView.right,
                    itemView.bottom
                )
                smsBackground!!.draw(c)

                val intrinsicWidth = smsIcon!!.intrinsicWidth
                val intrinsicHeight = smsIcon!!.intrinsicWidth

                val xMarkLeft = itemView.right - iconMargin - intrinsicWidth
                val xMarkRight = itemView.right - iconMargin
                val xMarkTop = itemView.top + (itemHeight - intrinsicHeight) / 2
                val xMarkBottom = xMarkTop + intrinsicHeight
                smsIcon!!.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom)

                smsIcon!!.draw(c)
            }
            dX > 0 -> {//swipe to right
                callBackground!!.setBounds(
                    itemView.left,
                    itemView.top,
                    itemView.left + dX.toInt(),
                    itemView.bottom
                )
                callBackground!!.draw(c)

                val intrinsicWidth = callIcon!!.intrinsicWidth
                val intrinsicHeight = callIcon!!.intrinsicWidth

                val xMarkLeft = itemView.left + iconMargin
                val xMarkRight = itemView.left + iconMargin + intrinsicWidth
                val xMarkTop = itemView.top + (itemHeight - intrinsicHeight) / 2
                val xMarkBottom = xMarkTop + intrinsicHeight
                callIcon!!.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom)

                callIcon!!.draw(c)
            }
            else -> return
        }

    }

}