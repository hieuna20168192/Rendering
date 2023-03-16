package com.example.rendering.promotion

import android.os.Trace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rendering.R
import com.example.rendering.services.Item

class EndorsingAdapter(
    private val onDimensionClick: (Dimension) -> Unit,
    private val onEndorsingClick: (Endorsing) -> Unit
) : ListAdapter<Dimension, EndorsingAdapter.VH>(ItemDiff) {

    lateinit var sharedPool: RecyclerView.RecycledViewPool

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val vHName = if (viewType == 0) "ComposingVH" else "ItemVH"
        Trace.beginSection(vHName)
        val VH = if (viewType == 0)
            ComposingVH(onEndorsingClick, inflater.inflate(R.layout.layout_item_compose, parent, false), sharedPool)
        else
            ItemVH(onDimensionClick, inflater.inflate(R.layout.layout_item_service, parent, false))
        return VH.also { Trace.endSection() }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0 else 1
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

    abstract class VH (private val listener: (Dimension) -> Unit, val binding: View) : RecyclerView.ViewHolder(binding) {

        open fun onBind(dimension: Dimension) {
            binding.run {
                setOnClickListener { listener(dimension) }
            }
        }
    }

    class ItemVH(listener: (Item) -> Unit, itemView: View) : VH(listener as (Dimension) -> Unit, itemView) {

        override fun onBind(item: Dimension) {
            Trace.beginSection("onBind#ItemVH")
            super.onBind(item)
            binding.run {
                findViewById<TextView>(R.id.tvId).text = item.id
                findViewById<TextView>(R.id.tvTitle).text = (item as Item).title
            }
            Trace.endSection()
        }
    }

    class ComposingVH(private val onEndorsingClick: (Endorsing) -> Unit, itemView: View, val sharedPool: RecyclerView.RecycledViewPool) : VH({}, itemView) {

        override fun onBind(item: Dimension) {
            Trace.beginSection("onBind#ComposingVH")
            binding.run {
                findViewById<RecyclerView>(R.id.rclEndorsing).setRecycledViewPool(sharedPool)

                findViewById<RecyclerView>(R.id.rclEndorsing).adapter = EndorsingAdapter {
                    onEndorsingClick(it)
                }.apply { submitList((item as EndorsedCompose).compose) }
            }
            Trace.endSection()
        }
    }

    class EndorsingAdapter(
        val listener: (Endorsing) -> Unit
    ): ListAdapter<Endorsing, EndorsingAdapter.EndorsingVH>(EndorsingDiff) {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): EndorsingVH {
            val inflater = LayoutInflater.from(parent.context)
            return EndorsingVH(listener, inflater.inflate(R.layout.layout_item_endorse, parent, false))
        }

        override fun onBindViewHolder(holder: EndorsingVH, position: Int) {
            holder.bind(getItem(position))
        }

        class EndorsingVH(val listener: (Endorsing) -> Unit, private val itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(endorsing: Endorsing) {
                Trace.beginSection("onBind#EndorsingVH")
                itemView.run {
                    setOnClickListener { listener(endorsing) }
                    findViewById<ImageView>(R.id.imgEndorsing).load(endorsing.url)
                }
                Trace.endSection()
            }
        }
    }
}
