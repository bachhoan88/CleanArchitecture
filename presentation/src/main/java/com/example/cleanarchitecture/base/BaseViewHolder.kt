package com.example.cleanarchitecture.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView


class BaseViewHolder<out T : ViewDataBinding> constructor(val binding: T) : RecyclerView.ViewHolder(binding.root)