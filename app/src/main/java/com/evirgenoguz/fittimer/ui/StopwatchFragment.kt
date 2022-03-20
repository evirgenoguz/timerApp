package com.evirgenoguz.fittimer.ui

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.evirgenoguz.fittimer.R
import com.evirgenoguz.fittimer.adapters.ModeAdapter
import com.evirgenoguz.fittimer.data.ModeDb
import com.evirgenoguz.fittimer.databinding.FragmentStopwatchBinding
import com.evirgenoguz.fittimer.models.ModeModel

class StopwatchFragment : Fragment() {

    private var _binding: FragmentStopwatchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ModeAdapter
    private val modeAdapter by lazy { ModeAdapter() }


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
            StartStopwatch()
        }

        binding.pauseButton.setOnClickListener {
            StopStopwatch()
        }

        binding.resetButton.setOnClickListener {
            ResetStopwatch()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun StartStopwatch() {
        //Start Stopwatch
        binding.stopwatch.base = SystemClock.elapsedRealtime()
        binding.stopwatch.start()

        //Invis Start Button
        binding.startButton.visibility = View.GONE

        //Visible Pause Button
        binding.pauseButton.visibility = View.VISIBLE
    }

    private fun StopStopwatch() {
        //Stop Stopwatch
        binding.stopwatch.stop()

        //Invis Pause button
        binding.pauseButton.visibility = View.GONE

        //ReVisible Start Button
        binding.startButton.visibility = View.VISIBLE
    }

    private fun ResetStopwatch(){
        //Set chronometer at 0
        binding.stopwatch.base = SystemClock.elapsedRealtime()

        //Stop Stopwatch
        binding.stopwatch.stop()

        //Invis Pause button
        binding.pauseButton.visibility = View.GONE

        //ReVisible Start Button
        binding.startButton.visibility = View.VISIBLE
    }

}