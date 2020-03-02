package no.kristiania.android.database.db

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import no.kristiania.android.database.Student

// Mention table and column name

object StudentTable : BaseColumns {
    const val TABLE_NAME = "STUDENT_TABLE"
    const val COLUMN_ID = "ID"
    const val COLUMN_NAME = "NAME"
}


class StudentDAO(context: Context) : BaseDataBase(context) {

    fun insert(name: String): Long {
        val contentValue = ContentValues()
        contentValue.put(StudentTable.COLUMN_NAME, name)
        return writableDatabase.insert(StudentTable.TABLE_NAME, null, contentValue)
    }

    fun fetchingAllRecord(): List<Student> {

        val cursor = readableDatabase.query(
            StudentTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val listStudent = mutableListOf<Student>()

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(StudentTable.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(StudentTable.COLUMN_NAME))
                listStudent.add(Student(id, name))
            }
        }

        return listStudent
    }


}