package cn.smaxlyb.lifecycledemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    private lateinit var mStartService: AppCompatButton
    private lateinit var mStopService: AppCompatButton

    private lateinit var mLocationListener: MyLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLocationListener = MyLocationListener(this, object : OnLocationChangedListener {
            override fun onChanged(latitude: Double, longitude: Double) {
                // do something
            }
        })

        lifecycle.addObserver(mLocationListener)

        initViews()
        initEvents()
    }

    fun initViews() {
        mStartService = findViewById(R.id.start_service)
        mStopService = findViewById(R.id.stop_service)
    }

    fun initEvents() {
        mStartService.setOnClickListener {
            val intent = Intent(this@MainActivity, MyService::class.java)
            startService(intent)
        }

        mStopService.setOnClickListener {
            val intent = Intent(this@MainActivity, MyService::class.java)
            stopService(intent)
        }
    }
}
