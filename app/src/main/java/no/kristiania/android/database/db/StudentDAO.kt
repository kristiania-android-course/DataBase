package no.kristiania.android.database.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import no.kristiania.android.database.StudentDTO
import no.kristiania.android.database.db.StudentTable.COLUMN_ID
import no.kristiania.android.database.db.StudentTable.COLUMN_NAME
import no.kristiania.android.database.db.StudentTable.TABLE_NAME


object StudentTable : BaseColumns {
    val TABLE_NAME = "student_table"
    val COLUMN_NAME = "name"
    val COLUMN_ID = "_id"
}

class StudentDAO(context: Context) : BaseDataBase(context) {

    fun insert(name: String): Long? {

        /*val query = " INSERT INTO $TABLE_NAME_STUDENTS ($COLUMN_NAME) VALUES ('$name')"
        writableDatabase.execSQL(query)*/

        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
        }

        return writableDatabase.use {
            it.insert(TABLE_NAME, null, values)
        }
        return 0
    }

    fun update(student: StudentDTO): Int? {
        val values = ContentValues().apply {
            put(COLUMN_NAME, student.name)
        }

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(student.id.toString())

        return writableDatabase.use {
            it.update(TABLE_NAME, values, selection, selectionArgs)
        }
    }

    fun fetchAll(): List<StudentDTO> {


        val cursor: Cursor = readableDatabase.query(TABLE_NAME, null, null, null, null, null, null)
        val studentsList = mutableListOf<StudentDTO>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
                studentsList.add(StudentDTO(id, name))
            }
        }
        return studentsList
    }

    fun delete(id: Long) {

    }
}