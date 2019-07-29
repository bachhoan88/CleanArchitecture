package com.example.cleanarchitecture.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.BaseRecyclerAdapter
import com.example.cleanarchitecture.databinding.CustomRepoItemBinding
import com.example.cleanarchitecture.model.RepoItem

class MainAdapter(
    private val dataBindingComponent: DataBindingComponent,
    private val callback: ((RepoItem) -> Unit)?
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
        DataBindingUtil.inflate<CustomRepoItemBinding>(
            LayoutInflater.from(parent.context), R.layout.custom_repo_item,
            parent, false, dataBindingComponent
        ).apply {
            root.setOnClickListener {
                this.repo?.let { item ->
                    callback?.invoke(item)
                }
            }
        }

    override fun bind(binding: ViewDataBinding, item: RepoItem) {
        if (binding is CustomRepoItemBinding) binding.repo = item
    }
}
