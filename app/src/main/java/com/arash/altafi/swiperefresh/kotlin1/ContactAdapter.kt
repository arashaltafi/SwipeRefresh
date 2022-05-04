package com.arash.altafi.swiperefresh.kotlin1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.swiperefresh.R
import com.arash.altafi.swiperefresh.kotlin.Users

class ContactAdapter(private var userList : ArrayList<Contact>) : RecyclerView.Adapter<ContactHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val item = userList[position]
        holder.txtName.text = item.name
        holder.txtFamily.text = item.family
    }

    override fun getItemCount(): Int =  userList.size

    fun onCall(context: Context, position: Int) {
        val item = userList[position]
        when {
            item.phone.isEmpty().not() -> {
                context.openCall(item.phone)
            }
            item.phone.isEmpty().not() -> {
                context.openCall(item.phone)
            }
            else -> { Toast.makeText(context , "شماره همراه در دسترس نیست!" , Toast.LENGTH_SHORT).show() }
        }
    }

    fun onSms(context: Context, position: Int) {
        val item = userList[position]
        when {
            item.phone.isEmpty().not() -> {
                context.openSMS(item.phone)
            }
            else -> { Toast.makeText(context , "شماره همراه در دسترس نیست!" , Toast.LENGTH_SHORT).show() }
        }
    }

}

class ContactHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var txtName : TextView = itemView.findViewById(R.id.txt_name)
    var txtFamily : TextView = itemView.findViewById(R.id.txt_family)
}