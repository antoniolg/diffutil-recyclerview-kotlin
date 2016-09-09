package com.antonioleiva.diffutilkotlin

import java.util.*

class ContentProvider {

    private val items = (1..10).map { Content(it, "Item $it", "http://lorempixel.com/200/200/animals/$it/") }

    fun generate(): List<Content> {
        val rand = Random(System.currentTimeMillis())
        return items.filter { rand.nextBoolean() }
    }
}