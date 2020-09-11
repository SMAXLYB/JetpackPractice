package cn.smaxlyb.roomdemo.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    fun insertStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Query("SELECT * FROM student")
    fun getAllStudent():LiveData<List<Student>>

    // 此处的id和方法参数的id名称要一致,否则编译出错
    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id:Int): Student
}