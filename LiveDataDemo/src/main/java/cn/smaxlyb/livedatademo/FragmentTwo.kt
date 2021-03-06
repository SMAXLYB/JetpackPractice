package cn.smaxlyb.livedatademo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class FragmentTwo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_two, container, false)

        val seekBar = rootView.findViewById<SeekBar>(R.id.seekBar)
        val shareDataViewModel =
            this.activity?.let { ViewModelProvider(it).get(ShareDataViewModel::class.java) }

        val liveData = shareDataViewModel?.getProg()
        liveData?.observe(viewLifecycleOwner) { seekBar.progress = it }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                liveData?.value = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        return rootView
    }
}