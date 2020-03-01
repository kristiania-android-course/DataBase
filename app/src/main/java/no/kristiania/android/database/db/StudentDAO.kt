package no.kristiania.android.database.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import no.kristiania.android.database.Student

// Mention table and column name

object StudentTable : BaseColumns {
    const val TABLE_NAME = "STUDENT_TABLE"
    const val COLUMN_ID = "ID"
    const val COLUMN_NAME = "NAME"
}


class StudentDAO(context: Context) : BaseDataBase(context) {

    // We haven't created table yet

    // create insert method
    fun insert(name: String): Long {

        // Use ContentValues to insert data
        val contentValue = ContentValues().apply {
            put(StudentTable.COLUMN_NAME, name)
        }

        // Returns the row index
        return writableDatabase.insert(StudentTable.TABLE_NAME, null, contentValue)
    }

    // Fetch all the records from the database
    fun fetchAllRecord(): List<Student> {
        /*val selection : String = "${StudentTable.COLUMN_NAME} = ?"
        val selectionArgs : Array<String> = arrayOf("Arun Pillai")*/

        val cursor: Cursor = readableDatabase.query(
            StudentTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        val studentList = mutableListOf<Student>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(StudentTable.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(StudentTable.COLUMN_NAME))
                studentList.add(Student(id, name))
            }
        }
        return studentList
    }



}