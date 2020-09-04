package cn.smaxlyb.navigationdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs


class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args : MainFragmentArgs by navArgs()
        val params = args.params
        Toast.makeText(context, "Params = $params", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val bundle = SecondFragmentArgs.Builder()
            .setUsername("Tom")
            .setAge(12)
            .build()
            .toBundle()

        // 方式1
        view.findViewById<Button>(R.id.btn_to_second_fragment).setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_mainFragment_to_secondFragment,bundle)
        }

        // 方式2,和方式1等价,做了一层封装
        // view.findViewById<Button>(R.id.btn_to_second_fragment).setOnClickListener(
        //     Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_secondFragment)
        // )

        return view
    }
}