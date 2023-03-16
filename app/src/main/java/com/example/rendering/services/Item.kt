package com.example.rendering.services

import androidx.recyclerview.widget.DiffUtil
import com.example.rendering.promotion.Dimension
import com.example.rendering.promotion.Endorsing

data class Item(
    override val id: String = "",
    val title: String = ""
): Dimension

val items = listOf(
    Item("item1", "Withdraw"),
    Item("item2", "Purchase"),
    Item("item3", "Chasing a girl"),
    Item("item4", "Follow a nerd"),
    Item("item5", "launch a new rocket"),
    Item("item6", "fly on the fire"),
    Item("item7", "over thinking"),
    Item("item8", "ride motorbike"),
    Item("item9", "shift in mind"),
    Item("item10", "gravitate toward drinking"),
    Item("item11", "control over thermal"),
    Item("item12", "escape from a bear"),
    Item("item13", "invite friend to birthday"),
    Item("item14", "quick sugar baby"),
    Item("item15", "become more intelligence"),
    Item("item16", "Meet Bill Gate!"),
)
