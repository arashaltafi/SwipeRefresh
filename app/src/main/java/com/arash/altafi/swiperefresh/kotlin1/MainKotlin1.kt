package com.arash.altafi.swiperefresh.kotlin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.swiperefresh.R
import com.arash.altafi.swiperefresh.kotlin.Users

class MainKotlin1 : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var contactAdapter : ContactAdapter
    private var contact : ArrayList<Contact> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin1)

        init()
    }

    private fun init() {
        bindViews()

        contact.add(Contact("Arash" , "Altafi", "09123456789"))
        contact.add(Contact("Jafar" , "Jafari", "09111111111"))
        contact.add(Contact("Reza" , "Sadeghi", ""))

        contactAdapter = ContactAdapter(contact)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainKotlin1 , RecyclerView.VERTICAL , false)
            setHasFixedSize(true)
            adapter = contactAdapter
            swipeSetup()
        }
    }

    private fun swipeSetup() {
        val swipeHelper = SwipeHelper(recyclerView, contactAdapter)
        ItemTouchHelper(swipeHelper).attachToRecyclerView(recyclerView)
    }

    private fun bindViews() {
        recyclerView = findViewById(R.id.rc_contact)
    }

}