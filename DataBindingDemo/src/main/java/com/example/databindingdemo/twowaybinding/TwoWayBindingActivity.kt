package com.example.databindingdemo.twowaybinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databindingdemo.R
import com.example.databindingdemo.databinding.ActivityTwoWayBindingBinding

class TwoWayBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityTwoWayBindingBinding>(this,
            R.layout.activity_two_way_binding)

        // binding.bindingObservable = TwoWayBindingObservable()
        binding.bindingObdservableField = TwoWayBindingObservableField()
    }
}