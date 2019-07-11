package com.app.fooddelivery.common

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import com.app.fooddelivery.listeners.OnPaginationCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/*******
 * Keep all the static binding adapters here
 * Author: Lajesh Dineshkumar
 * Created on: 2019-07-10
 * Modified on: 2019-07-10
 ********/
class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("bind:image")
        fun loadImage(view: ImageView, imageUrl: String?) {
            Glide.with(view.context)
                .load(imageUrl).apply(RequestOptions().circleCrop())
                .into(view)
        }

        @JvmStatic
        @BindingAdapter("paginationCallback")
        fun setSwipRefreshListener(
            view: androidx.recyclerview.widget.RecyclerView,
            listener: OnPaginationCallback?
        ) {
            listener?.let {
                view.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: androidx.recyclerview.widget.RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        it.onNextPage(
                            recyclerView.layoutManager?.itemCount ?: 0,
                            (recyclerView.layoutManager as androidx.recyclerview.widget.LinearLayoutManager).findLastVisibleItemPosition()
                        )
                    }
                })
            }

        }

        @JvmStatic
        @BindingAdapter("swipeListener", "colorScheme", "hideRefresh", requireAll = false)
        fun setSwipRefreshListener(
            view: androidx.swiperefreshlayout.widget.SwipeRefreshLayout,
            listener: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener?, @ColorRes color: Int?, refresh: Boolean?
        ) {
            refresh?.let {
                if (it) {
                    view.isRefreshing = !it
                }
            }
            listener?.let {
                view.setOnRefreshListener(listener)
            }
            color?.let {
                view.setColorSchemeColors(it)
            }
        }
    }




}