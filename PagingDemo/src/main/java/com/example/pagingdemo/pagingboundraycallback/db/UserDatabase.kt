package com.example.pagingdemo.pagingboundraycallback.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pagingdemo.pagingboundraycallback.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    // 数据库单例
    companion object {
        private const val DATABASE_NAME = "user_db";

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            UserDatabase::class.java,
                            DATABASE_NAME)
                            .build()
                    }
                }
            }

            return INSTANCE as UserDatabase
        }
    }

    // 返回Dao
    abstract fun userDao(): UserDao
}