package com.example.pagingdemo.pagingboundraycallback.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pagingdemo.pagingboundraycallback.model.User

@Dao()
interface UserDao {
    @Insert
    fun insertUser(users: List<User>)

    @Query("DELETE FROM user")
    fun clear()

    //　直接返回数据工厂,传给pagedList
    @Query("SELECT * FROM user")
    fun getUserList(): DataSource.Factory<Int, User>
}