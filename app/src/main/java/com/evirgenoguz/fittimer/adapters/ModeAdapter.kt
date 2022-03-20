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

class ModeAdapter(): RecyclerView.Adapter<ModeAdapter.ModeViewHolder>() {

    private val modeList = arrayListOf<ModeModel>()


    class ModeViewHolder(private val modeCardBinding: ModeCardBinding): RecyclerView.ViewHolder(modeCardBinding.root){
        fun bind(mode: ModeModel){
            modeCardBinding.modeItem = mode
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModeAdapter.ModeViewHolder {
        val modeCardBinding = ModeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModeViewHolder(modeCardBinding)
    }

    override fun onBindViewHolder(holder: ModeAdapter.ModeViewHolder, position: Int) {
        holder.bind(modeList[position])
    }

    override fun getItemCount(): Int {
        return modeList.size

    }

    fun updateList(updatedList: List<ModeModel>){
        modeList.clear()
        modeList.addAll(updatedList)
        notifyDataSetChanged()
    }


}


