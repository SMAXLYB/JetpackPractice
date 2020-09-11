package cn.smaxlyb.roomdemo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//标记此类为实体类,后续会通过注解映射到数据库的字段
@Entity(tableName = "student")
data class Student(
    // 主键,自动生成
    @PrimaryKey(autoGenerate = true)
    // 列名和类型
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    val id: Int?,

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    var name: String,

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    var age: Int
) {

    // 忽略字段,不会被映射
    @Ignore
    constructor(name: String, age: Int) : this(null, name, age)

}
