package com.example.cleanarchitecture.base

import android.support.v7.widget.RecyclerView
import android.view.View


abstract class BaseViewHolder constructor(
        itemView: View
) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(position: Int)
}