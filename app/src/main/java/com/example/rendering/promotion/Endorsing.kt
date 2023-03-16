package com.example.rendering.promotion

import androidx.recyclerview.widget.DiffUtil
import com.example.rendering.services.Item
import com.example.rendering.services.items

interface Dimension {
    val id: String
}

data class Endorsing(
    val id: String = "",
    val url: String = ""
)

object EndorsingDiff : DiffUtil.ItemCallback<Endorsing>() {
    override fun areItemsTheSame(oldItem: Endorsing, newItem: Endorsing) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Endorsing, newItem: Endorsing) = oldItem.url == newItem.url
}

data class EndorsedCompose(
    override val id: String = "",
    val compose: List<Endorsing> = endorses
) : Dimension {

    fun hash() = compose.joinToString("_") { it.id }
}

object ItemDiff : DiffUtil.ItemCallback<Dimension>() {
    override fun areItemsTheSame(oldItem: Dimension, newItem: Dimension) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Dimension, newItem: Dimension) = when {
        oldItem is EndorsedCompose && newItem is EndorsedCompose -> oldItem.hash() == newItem.hash()
        oldItem is Item && newItem is Item -> oldItem.title == newItem.title
        else -> throw RuntimeException("Undefined Item Type!")
    }
}

val endorses = listOf(
    Endorsing("endorsing1", "https://picsum.photos/id/1/480/960"),
    Endorsing("endorsing2", "https://picsum.photos/id/2/480/960"),
    Endorsing("endorsing3", "https://picsum.photos/id/3/480/960"),
    Endorsing("endorsing4", "https://picsum.photos/id/4/480/960"),
    Endorsing("endorsing5", "https://picsum.photos/id/5/480/960"),
    Endorsing("endorsing6", "https://picsum.photos/id/6/480/960"),
    Endorsing("endorsing7", "https://picsum.photos/id/7/480/960"),
    Endorsing("endorsing8", "https://picsum.photos/id/8/480/960"),
    Endorsing("endorsing9", "https://picsum.photos/id/9/480/960"),
    Endorsing("endorsing10", "https://picsum.photos/id/10/480/960"),
    Endorsing("endorsing11", "https://picsum.photos/id/11/480/960"),
    Endorsing("endorsing12", "https://picsum.photos/id/12/480/960"),
    Endorsing("endorsing13", "https://picsum.photos/id/13/480/960"),
    Endorsing("endorsing14", "https://picsum.photos/id/14/480/960"),
)

val dimensions = mutableListOf<Dimension>().apply {
    add(EndorsedCompose())
    addAll(items)
}
