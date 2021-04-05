package com.example.workoutapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler_view_card.view.*

class ExerciseAdapter(val context : Context ,
                      val arr: ArrayList<ExerciseModel>):
        RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    class ViewHolder( itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvItem = itemView.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view_card
        ,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = arr[position]
        holder.tvItem.text = model.getId().toString()

        if(model.getIsSelected()){
            holder.tvItem.background = ContextCompat.getDrawable(context,
                    R.drawable.circular_item_color_background)

            holder.tvItem.setTextColor(ContextCompat.getColor(context,R.color.green))
        }else if(model.getIsCompleted()){
            holder.tvItem.background = ContextCompat.getDrawable(context,
                    R.drawable.item_circular_color_green_background)

            holder.tvItem.setTextColor(ContextCompat.getColor(context,R.color.white))
        }else{
            holder.tvItem.background = ContextCompat.getDrawable(context,
                    R.drawable.item_circular_color_gray_background)

            holder.tvItem.setTextColor(ContextCompat.getColor(context,R.color.green))
        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }



}