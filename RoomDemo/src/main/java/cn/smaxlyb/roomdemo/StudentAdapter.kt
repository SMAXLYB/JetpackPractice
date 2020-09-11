package cn.smaxlyb.roomdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import cn.smaxlyb.roomdemo.database.Student

class StudentAdapter(
    private val context: Context,
    private val data: List<Student>
) : BaseAdapter() {

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Student = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        val holder: Holder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_student, null)
            holder = Holder(
                view.findViewById(R.id.tvId),
                view.findViewById(R.id.tvName),
                view.findViewById(R.id.tvAge)
            )
            view.tag = holder
        } else {
            holder = convertView.tag as Holder
        }
        val student = data[position]
        holder.tvId.text = student.id.toString()
        holder.tvName.text = student.name
        holder.tvAge.text = student.age.toString()
        return view as View
    }

    inner class Holder(val tvId: TextView, val tvName: TextView, val tvAge: TextView)
}
