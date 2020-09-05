package cn.smaxlyb.viewmodeldemo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniComponent()
    }

    private fun iniComponent() {
        val tvTime = findViewById<TextView>(R.id.tvTime)

        val timerViewModel = ViewModelProvider(this)[TimerViewModel::class.java]
        timerViewModel.setOnTimeChangeListener { time ->
            runOnUiThread {
                tvTime.text = "Time : $time"
            }
        }

        timerViewModel.startTiming()
    }
}
