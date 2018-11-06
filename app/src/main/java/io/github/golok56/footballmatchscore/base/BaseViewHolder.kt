package io.github.golok56.footballmatchscore.base

import androidx.recyclerview.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<T>(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    abstract fun bindItem(item: T)
}