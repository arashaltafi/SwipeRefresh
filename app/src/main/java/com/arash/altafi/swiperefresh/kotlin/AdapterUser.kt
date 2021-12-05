package com.arash.altafi.swiperefresh.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.swiperefresh.R

class AdapterUser(private var userList : ArrayList<Users>) : RecyclerView.Adapter<UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val item = userList[position]
        holder.txtName.text = item.name
        holder.txtFamily.text = item.family
    }

    override fun getItemCount(): Int =  userList.size

}

class UserHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var txtName : TextView = itemView.findViewById(R.id.txt_name)
    var txtFamily : TextView = itemView.findViewById(R.id.txt_family)

}