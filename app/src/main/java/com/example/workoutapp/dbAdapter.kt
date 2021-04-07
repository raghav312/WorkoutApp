package com.example.workoutapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history_row.view.*

class dbAdapter(val context: Context , val items: ArrayList<String>)
    :RecyclerView.Adapter<dbAdapter.ViewHolder>() {

    // get all the ids from view to a variable from here
    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {

        val tvItem = view.tvItem!!
        val tvPosition = view.tvPosition!!
        val llHistoryItemMain = view.ll_history_item_main!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history_row, parent,false ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date: String = items.get(position)
        holder.tvItem.text =date
        holder.tvPosition.text = (position+1).toString()

        if(position%2 == 0){
            holder.llHistoryItemMain.setBackgroundColor(
                    Color.parseColor("#EBEBEB")
            )
        }else{
            holder.llHistoryItemMain.setBackgroundColor(
                    Color.parseColor("#FFFFFF")
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}