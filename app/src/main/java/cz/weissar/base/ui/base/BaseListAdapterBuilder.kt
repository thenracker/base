package cz.weissar.base.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapterBuilder<T>(private val context: Context) {

    var adapter = Adapter(context)
        private set

    @LayoutRes
    abstract fun getRowLayout(): Int

    abstract fun View.onBind(item: T)

    open fun itemsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean {
        return oldItem == newItem
    }

    open fun contentsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean {
        return oldItem == newItem
    }

    fun getString(@StringRes id: Int) = context.getString(id)
    fun getDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(context, id)
    fun getColor(@ColorRes id: Int) = ContextCompat.getColor(context, id)

    inner class Adapter(private val context: Context) :
        ListAdapter<T, Adapter.Holder>(
            object : DiffUtil.ItemCallback<T>() {
                override fun areItemsTheSame(
                    oldItem: T,
                    newItem: T
                ): Boolean {
                    return itemsTheSame(oldItem, newItem)
                }

                override fun areContentsTheSame(
                    oldItem: T,
                    newItem: T
                ): Boolean {
                    return contentsTheSame(oldItem, newItem)
                }
            }) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            Holder(LayoutInflater.from(context).inflate(getRowLayout(), parent, false))

        override fun onBindViewHolder(holder: Holder, position: Int) =
            holder.bind(getItem(position))

        inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(item: T) = with(itemView) {
                onBind(item)
            }
        }
    }

}
