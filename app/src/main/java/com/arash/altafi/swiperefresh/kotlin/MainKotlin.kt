package com.arash.altafi.swiperefresh.kotlin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arash.altafi.swiperefresh.R

class MainKotlin : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : AdapterUser
    private lateinit var swipe : SwipeRefreshLayout
    private var users : ArrayList<Users> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user)

        init()
    }

    private fun init() {
        bindViews()

        users.add(Users("Arash" , "Altafi"))
        users.add(Users("Jafar" , "Jafari"))
        users.add(Users("Reza" , "Sadeghi"))

        adapter = AdapterUser(users)
        recyclerView.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)
        recyclerView.adapter = adapter

        swipe.setOnRefreshListener {
            swipe.setColorSchemeColors(Color.RED , Color.BLUE , Color.GREEN , Color.YELLOW)
            Handler(Looper.myLooper()!!).postDelayed(Runnable {
                adapter.notifyDataSetChanged()
                swipe.isRefreshing = false
            } , 4000)
        }

        // swipe users to delete
        val swipeHandler = object : SwipeToDeleteCallback(this) { }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    private fun bindViews() {
        recyclerView = findViewById(R.id.rc_user)
        swipe = findViewById(R.id.swipe_refresh_layout)
    }

}