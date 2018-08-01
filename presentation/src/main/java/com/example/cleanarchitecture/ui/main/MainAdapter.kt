package com.example.cleanarchitecture.ui.main

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.BaseRecyclerAdapter
import com.example.cleanarchitecture.databinding.CustomRepoItemBinding
import com.example.cleanarchitecture.databinding.CustomRepoItemSeparatorBinding
import com.example.cleanarchitecture.model.RepoItem

class MainAdapter(
        private val dataBindingComponent: DataBindingComponent,
        private val callback: ((RepoItem) -> Unit)?,
        private val callbackSeparator: (() -> Unit)?
) : BaseRecyclerAdapter<RepoItem>(
        callBack = object : DiffUtil.ItemCallback<RepoItem>() {
            override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
                return oldItem.name == newItem.name && oldItem.description == newItem.description
            }

        }) {

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding =
            when (viewType) {
                VIEW_TYPE_SEPARATOR -> {
                    DataBindingUtil.inflate<CustomRepoItemSeparatorBinding>(
                            LayoutInflater.from(parent.context), R.layout.custom_repo_item_separator,
                            parent, false, dataBindingComponent
                    ).apply {
                        root.setOnClickListener {
                            callbackSeparator?.invoke()
                        }
                    }
                }
                else -> {
                    DataBindingUtil.inflate<CustomRepoItemBinding>(
                            LayoutInflater.from(parent.context), R.layout.custom_repo_item,
                            parent, false, dataBindingComponent
                    ).apply {
                        root.setOnClickListener {
                            this.repo?.let { callback?.invoke(it) }
                        }
                    }
                }
            }

    override fun bind(binding: ViewDataBinding, item: RepoItem) {
        if (binding is CustomRepoItemBinding) binding.repo = item
    }


    /**
     * this method is for demonstration
     *
     */
    override fun getItemViewType(position: Int): Int =
            when (position % 2) {
                0 -> VIEW_TYPE_NORMAL
                else -> VIEW_TYPE_SEPARATOR
            }


    companion object {
        const val VIEW_TYPE_NORMAL = 0
        const val VIEW_TYPE_SEPARATOR = 1
    }
}
