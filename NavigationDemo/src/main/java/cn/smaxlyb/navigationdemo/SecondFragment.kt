package cn.smaxlyb.navigationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.navArgs

class SecondFragment : Fragment() {
    private lateinit var mSendNotification: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: SecondFragmentArgs by navArgs()
        val userName = args.username
        val age = args.age
        Toast.makeText(context, "UserName : $userName, Age : $age", Toast.LENGTH_SHORT).show()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_second, container, false)
        mSendNotification = rootView.findViewById(R.id.send_notification)
        mSendNotification.setOnClickListener {
            sendNotification()
        }
        return rootView
    }

    //　使用PendingIntent
    private fun sendNotification() {
        //如果没有上下文,不执行方法
        if (activity == null) {
            return
        }

        // 如果8.0以上系统,新增通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 设置通知等级
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            // 创建频道
            val channel = NotificationChannel("1", "ChannelName", importance)
            channel.description = "description"
            val notificationManager = activity?.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(requireActivity(), "1")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("Hello world")
            .setContentTitle("DeepLinkTest")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(getPendingIntent())
            .setAutoCancel(true)
            .build()
        val notificationManager = NotificationManagerCompat.from(requireActivity())
        notificationManager.notify(1, notification)
    }

    private fun getPendingIntent(): PendingIntent? {
        if (activity != null) {
            val bundle = SecondFragmentArgs.Builder()
                .setUsername("from notification")
                .build()
                .toBundle()

            // 如果有navController,可以调用createDeepLink()
            return context?.let {
                NavDeepLinkBuilder(it)
                    .setGraph(R.navigation.nav_graph)
                    .setDestination(R.id.secondFragment)
                    .setArguments(bundle)
                    .createPendingIntent()
            }
        }

        return null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

}