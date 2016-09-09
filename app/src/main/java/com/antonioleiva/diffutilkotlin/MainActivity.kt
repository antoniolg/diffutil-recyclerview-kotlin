package com.antonioleiva.diffutilkotlin

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val provider = ContentProvider()
    val adapter = ContentAdapter()
    val handler = Handler()
    val runnable = { fillAdapter(); scheduleReload() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = adapter
        runnable()
    }

    private fun scheduleReload() {
        handler.postDelayed(runnable, 4000)
    }

    private fun fillAdapter() {
        val oldItems = adapter.items
        adapter.items = provider.generate()
        adapter.notifyChanges(oldItems, adapter.items)
    }
}
