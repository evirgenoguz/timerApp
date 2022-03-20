package com.evirgenoguz.fittimer.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentHostCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.evirgenoguz.fittimer.MainActivity
import com.evirgenoguz.fittimer.R
import com.evirgenoguz.fittimer.TimerService
import com.evirgenoguz.fittimer.adapters.ModeAdapter
import com.evirgenoguz.fittimer.data.ModeDb
import com.evirgenoguz.fittimer.databinding.FragmentStopwatchBinding
import com.evirgenoguz.fittimer.models.ModeModel
import java.util.*
import kotlin.math.roundToInt

class StopwatchFragment : Fragment() {

    private var _binding: FragmentStopwatchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ModeAdapter
    private val modeAdapter by lazy { ModeAdapter() }
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStopwatchBinding.inflate(inflater, container, false)
        val view = binding.root

        val modeList = ModeDb.getModes()

        modeAdapter.updateList(modeList)

        binding.modeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = modeAdapter
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            startStopwatch()
        }

        binding.pauseButton.setOnClickListener {
            stopStopwatch()
        }

        binding.resetButton.setOnClickListener {
            resetStopwatch()
        }

        serviceIntent  = Intent(context, TimerService::class.java)
        requireActivity().registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))


        binding.apply {
            openModeButton.setOnClickListener{
                (modeRecyclerView.layoutParams as LinearLayout.LayoutParams).weight = 0.5F
                (firstBlockStopWatchFragment.layoutParams as LinearLayout.LayoutParams).weight = 0.3F
                (thirdBlockStopWatchFragment.layoutParams as LinearLayout.LayoutParams).weight = 0.2F
                modeRecyclerView.visibility = View.VISIBLE
                openModeButton.visibility = View.GONE
                closeModeButton.visibility = View.VISIBLE
            }

            closeModeButton.setOnClickListener {
                (modeRecyclerView.layoutParams as LinearLayout.LayoutParams).weight = 0.0F
                (firstBlockStopWatchFragment.layoutParams as LinearLayout.LayoutParams).weight = 0.8F
                (thirdBlockStopWatchFragment.layoutParams as LinearLayout.LayoutParams).weight = 0.2F
                modeRecyclerView.visibility = View.GONE
                closeModeButton.visibility = View.GONE
                openModeButton.visibility = View.VISIBLE
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver(){

        override fun onReceive(context: Context?, intent: Intent?) {
            time = intent!!.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            binding.timeTv.text = getTimeStringFromDouble(time)

        }

    }

    private fun getTimeStringFromDouble(time: Double): String{
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, minute: Int, second: Int): String = String.format("%02d:%02d:%02d", hour, minute, second)

    private fun startStopwatch() {
        //Start Stopwatch
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        requireActivity().startService(serviceIntent)

        //Invis Start Button
        binding.startButton.visibility = View.GONE

        //Visible Pause Button
        binding.pauseButton.visibility = View.VISIBLE
    }

    private fun stopStopwatch() {
        //Stop Stopwatch
        requireActivity().stopService(serviceIntent)


        //Invis Pause button
        binding.pauseButton.visibility = View.GONE

        //ReVisible Start Button
        binding.startButton.visibility = View.VISIBLE
    }

    private fun resetStopwatch(){
        //Set chronometer at 0
        stopStopwatch()

        time = 0.0
        binding.timeTv.text = getTimeStringFromDouble(time)
        //Stop Stopwatch


        //Invis Pause button
        binding.pauseButton.visibility = View.GONE

        //ReVisible Start Button
        binding.startButton.visibility = View.VISIBLE
    }

}