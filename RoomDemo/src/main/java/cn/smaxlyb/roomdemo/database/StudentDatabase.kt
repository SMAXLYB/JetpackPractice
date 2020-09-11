package cn.smaxlyb.roomdemo.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Student::class], version = 4)
abstract class StudentDatabase : RoomDatabase() {

    companion object {
        private const val TAG = "StudentDatabase"
        private const val DATABASE_NAME = "db_student"

        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase(context: Context): StudentDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            StudentDatabase::class.java,
                            DATABASE_NAME
                        )
                            // 从Asset的db文件预先加载数据
                            .createFromAsset("databases/students.db")
                            // 从自定义文件中加载
                            // .createFromFile()
                            // 升级数据库操作
                            .addMigrations(
                                MIGRATION_1_to_2, MIGRATION_2_to_3,
                                MIGRATION_1_to_3, MIGRATION_3_to_4
                            )
                            // 升级数据库异常时,会重新创建数据表,数据也会丢失
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE as StudentDatabase
        }

        // 数据库升级操作
        private val MIGRATION_1_to_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d(TAG, "migrate: 1 to 2")
            }
        }
        private val MIGRATION_2_to_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d(TAG, "migrate: 2 to 3")
            }
        }

        // 如果从版本1直接升到版本3的方案可用,那么就会用这个方案,否则先后顺序执行1-2,2-3的方案
        private val MIGRATION_1_to_3: Migration = object : Migration(1, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d(TAG, "migrate: 1 to 3")
            }
        }
        private val MIGRATION_3_to_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d(TAG, "migrate: 3 to 4")
                database.execSQL(
                    "CREATE TABLE temp_Student(" +
                            "id INTEGER PRIMARY KEY," +
                            "name TEXT NOT NULL," +
                            "age INTEGER NOT NULL" +
                            ")"
                )
                database.execSQL(
                    "INSERT INTO temp_Student (id, name, age)" +
                            "SELECT id, name, age FROM Student"
                )
                database.execSQL("DROP TABLE Student")
                database.execSQL("ALTER TABLE temp_Student RENAME TO Student")
            }
        }
    }

    // 获取dao
    abstract fun studentDao(): StudentDao
}
