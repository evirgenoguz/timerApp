package com.evirgenoguz.fittimer.data

import com.evirgenoguz.fittimer.models.ModeModel

object ModeDb {

    fun getModes(): ArrayList<ModeModel>{
        val modeList: ArrayList<ModeModel> = ArrayList()

        modeList.add(ModeModel("00:45", "Plank"))
        modeList.add(ModeModel("00:15", "Dinlenme"))
        modeList.add(ModeModel("00:45", "Russian Twist"))
        modeList.add(ModeModel("00:15", "Dinlenme"))
        modeList.add(ModeModel("00:15", "Flutter"))

        modeList.add(ModeModel("00:45", "Plank"))
        modeList.add(ModeModel("00:15", "Dinlenme"))
        modeList.add(ModeModel("00:45", "Russian Twist"))
        modeList.add(ModeModel("00:15", "Dinlenme"))
        modeList.add(ModeModel("00:15", "Flutter"))

        return modeList
    }

}