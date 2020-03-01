package no.kristiania.android.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "STUDENT_TABLE")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id: Long,
    @ColumnInfo(name = "NAME")
    val name: String?
)