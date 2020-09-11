package cn.smaxlyb.roomdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cn.smaxlyb.roomdemo.database.Student
import cn.smaxlyb.roomdemo.database.StudentDatabase

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private var mStudentDatabase: StudentDatabase = StudentDatabase.getDatabase(application)
    private lateinit var mLiveDataStudent: LiveData<List<Student>>

    init {
        mLiveDataStudent = mStudentDatabase.studentDao().getAllStudent()
    }

    fun getLiveDataStudent(): LiveData<List<Student>> = mLiveDataStudent
}