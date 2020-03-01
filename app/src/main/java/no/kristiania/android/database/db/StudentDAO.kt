package no.kristiania.android.database.db

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns

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
        val contentValue = ContentValues().apply {
            put(StudentTable.COLUMN_NAME, name)
        }
        return writableDatabase.insert(StudentTable.TABLE_NAME, null, contentValue)
    }


}