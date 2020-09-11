package cn.smaxlyb.roomdemo

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cn.smaxlyb.roomdemo.database.Student
import cn.smaxlyb.roomdemo.database.StudentDatabase
import java.lang.ref.WeakReference


class MainActivity : AppCompatActivity() {
    private lateinit var mListView: ListView
    private lateinit var mBtnInsertStudent: Button
    private lateinit var mStudentList: MutableList<Student>
    private lateinit var mStudentAdapter: StudentAdapter
    private lateinit var mStudentDatabase: StudentDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initData()
        initEvents()
    }

    private fun initViews() {
        mListView = findViewById(R.id.lvStudent)
        mBtnInsertStudent = findViewById(R.id.btnInsertStudent)
    }

    private fun initData() {
        mStudentList = ArrayList<Student>()
        mStudentAdapter = StudentAdapter(this, mStudentList)
        mListView.adapter = mStudentAdapter
        mStudentDatabase = StudentDatabase.getDatabase(this)

        // 当数据发生变化,自动通知UI
        val studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        studentViewModel.getLiveDataStudent().observe(this){
            mStudentList.clear()
            mStudentList.addAll(it)
            mStudentAdapter.notifyDataSetChanged()
        }
    }

    private fun initEvents() {
        mBtnInsertStudent.setOnClickListener {
            openAddStudentDialog()
        }

        mListView.setOnItemLongClickListener { parent, view, position, id ->
            updateOrDeleteDialog(mStudentList[position])
            false
        }
    }

    /*--------------弹出对话框---------------*/
    private fun updateOrDeleteDialog(student: Student) {
        val dialog = AlertDialog.Builder(this@MainActivity)
            .setTitle("选择操作")
            .setItems(arrayOf("更新", "删除")) { _, which ->
                when (which) {
                    0 ->
                        openUpdateStudentDialog(student)
                    1 ->
                        DeleteStudentTask(this).execute(student)
                }
            }
            .create()
            .show()
    }

    private fun openUpdateStudentDialog(student: Student) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_student, null)
        val etName = dialogView.findViewById<EditText>(R.id.et_student_name)
        val etAge = dialogView.findViewById<EditText>(R.id.et_student_age)
        etName.text = Editable.Factory.getInstance().newEditable(student.name)
        etAge.text = Editable.Factory.getInstance().newEditable(student.age.toString())
        val dialog = AlertDialog.Builder(this)
            .setTitle("Update Student")
            .setView(dialogView)
            .setPositiveButton("确认") { _, _ ->
                if (TextUtils.isEmpty(etName.text.toString()) || TextUtils.isEmpty(etAge.text.toString())) {
                    Toast.makeText(this@MainActivity, "输入不能为空", Toast.LENGTH_SHORT).show()
                } else {
                    student.name = etName.text.toString()
                    student.age = etAge.text.toString().toInt()
                    UpdateStudentTask(this).execute(student)
                }
            }
            .setNegativeButton("取消") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun openAddStudentDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_student, null)
        val etName = dialogView.findViewById<EditText>(R.id.et_student_name)
        val etAge = dialogView.findViewById<EditText>(R.id.et_student_age)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Add Student")
            .setView(dialogView)
            .setPositiveButton("确定") { _, _ ->
                if (TextUtils.isEmpty(etName.text.toString()) ||
                    TextUtils.isEmpty(etAge.text.toString())
                ) {
                    Toast.makeText(this@MainActivity, "输入不能为空", Toast.LENGTH_SHORT).show()
                } else {
                    InsertStudentTask(
                        this,
                        etName.text.toString(),
                        etAge.text.toString().toInt()
                    ).execute()
                }
            }
            .setNegativeButton("取消") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    /*--------------异步访问数据库---------------*/
    class DeleteStudentTask(private val activity: MainActivity) : AsyncTask<Student, Unit, Unit>() {
        private val weakReference: WeakReference<MainActivity> = WeakReference(activity)

        override fun doInBackground(vararg params: Student?) {
            val student = params[0]
            weakReference.get()?.let {
                if (student != null) {
                    it.mStudentDatabase.studentDao().deleteStudent(student)
                }
            }
        }
    }

    class UpdateStudentTask(private val activity: MainActivity) : AsyncTask<Student, Unit, Unit>() {
        private var weakReference = WeakReference<MainActivity>(activity)

        override fun doInBackground(vararg params: Student?) {
            val student = params[0]
            weakReference.get()?.let {
                if (student != null) {
                    it.mStudentDatabase.studentDao().updateStudent(student)
                }
            }
        }
    }

    class InsertStudentTask(
        private val activity: MainActivity,
        private val name: String,
        private val age: Int
    ) : AsyncTask<Unit, Unit, Unit>() {
        private var weakReference: WeakReference<MainActivity> = WeakReference(activity)

        override fun doInBackground(vararg params: Unit) {
            // 操作数据库
            weakReference.get()?.let {
                it.mStudentDatabase.studentDao().insertStudent(Student(name, age))
            }
        }
    }
}