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
import com.example.cleanarchitecture.model.RepoItem

class MainAdapter(
        private val dataBindingComponent: DataBindingComponent,
        private val callback: ((RepoItem) -> Unit)?
) : BaseRecyclerAdapter<RepoItem, CustomRepoItemBinding> (
    callBack = object : DiffUtil.ItemCallback<RepoItem>() {
        override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
            return oldItem.name == newItem.name && oldItem.description == newItem.description
        }

    }) {

    override fun createBinding(parent: ViewGroup): CustomRepoItemBinding {
        val binding = DataBindingUtil.inflate<CustomRepoItemBinding>(
                LayoutInflater.from(parent.context), R.layout.custom_repo_item, parent, false, dataBindingComponent
        )

        binding.root.setOnClickListener { binding.repo?.let { callback?.invoke(it) } }

        return binding
    }

    override fun bind(binding: CustomRepoItemBinding, item: RepoItem) {
        binding.repo = item
    }
}