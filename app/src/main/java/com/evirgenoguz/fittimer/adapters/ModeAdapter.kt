package com.evirgenoguz.fittimer.adapters

import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evirgenoguz.fittimer.R
import com.evirgenoguz.fittimer.databinding.ModeCardBinding
import com.evirgenoguz.fittimer.models.ModeModel

class ModeAdapter(private val modeList: List<ModeModel>): RecyclerView.Adapter<ModeAdapter.ModeViewHolder>() {

    inner class ModeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var itemTime: TextView
        var itemDescription: TextView

        init{
            itemTime = itemView.findViewById(R.id.itemTime)
            itemDescription = itemView.findViewById(R.id.itemDescription)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModeAdapter.ModeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mode_card, parent, false)
        return ModeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModeAdapter.ModeViewHolder, position: Int) {
        holder.itemTime.text = modeList[position].time
        holder.itemDescription.text = modeList[position].description
    }

    override fun getItemCount(): Int {
        return modeList.size

    }


}


