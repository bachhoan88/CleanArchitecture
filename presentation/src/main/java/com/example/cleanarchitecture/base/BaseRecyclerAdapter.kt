package com.example.cleanarchitecture.base

import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import java.util.concurrent.Executors


abstract class BaseRecyclerAdapter<T, V : ViewDataBinding>(
        callBack: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseViewHolder<V>>(
        AsyncDifferConfig.Builder<T>(callBack)
                .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
                .build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        return BaseViewHolder(createBinding(parent))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        bind(holder.binding, getItem(position))
        holder.binding.executePendingBindings()
    }

    protected abstract fun createBinding(parent: ViewGroup): V

    protected abstract fun bind(binding: V, item: T)
}