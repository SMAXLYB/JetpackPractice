package cn.smaxlyb.navigationdemo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavController = findNavController(R.id.nav_host_fragment)
        // 设置切换监听
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            Toast.makeText(this@MainActivity,"destination id = " + destination.id, Toast.LENGTH_SHORT).show()
        }
        mAppBarConfiguration = AppBarConfiguration(mNavController.graph)
        // 关联AppBar和导航
        setupActionBarWithNavController(mNavController, mAppBarConfiguration)

        // 关联底部菜单
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
            .setupWithNavController(mNavController)
    }

    // 使操作栏支持导航
    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp(mAppBarConfiguration) || super.onSupportNavigateUp()
    }

    // 使菜单栏支持导航
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(mNavController) || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_second, menu!!)
        return super.onCreateOptionsMenu(menu)
    }
}
