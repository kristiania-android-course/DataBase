package no.kristiania.android.database.db

import android.provider.BaseColumns
import androidx.room.*
import no.kristiania.android.database.Student

// Mention table and column name

object StudentTable : BaseColumns {
    const val TABLE_NAME = "STUDENT_TABLE"
    const val COLUMN_ID = "ID"
    const val COLUMN_NAME = "NAME"
}

@Dao
interface StudentDAO {

    @Insert
    fun insert(student: Student)

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM STUDENT_TABLE")
    fun fetchAllRecord(): List<Student>

    @Query("SELECT * FROM STUDENT_TABLE where ID = :studentID")
    fun getRecordWithID(studentID: Long): Student

    /*Expected:
    TableInfo{name='STUDENT_TABLE', columns={ID=Column{name='ID', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='null'}, NAME=Column{name='NAME', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[]}
    Found:
    TableInfo{name='STUDENT_TABLE', columns={ID=Column{name='ID', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=1, defaultValue='null'}, NAME=Column{name='NAME', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[]}*/


/*

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

    fun getRecordWithID(studentID: Long): Student {
        // where clause to use
        val selection = "${StudentTable.COLUMN_ID} = ?"

        // arguments for the where clause
        val selectionArgs = arrayOf(studentID.toString())

        val cursor: Cursor = readableDatabase.query(
            StudentTable.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        return with(cursor) {
            moveToFirst()
            // Please see the cursor documentation for these methods
            val id = getLong(getColumnIndexOrThrow(StudentTable.COLUMN_ID))
            val name = getString(getColumnIndexOrThrow(StudentTable.COLUMN_NAME))
            Student(id, name)
        }
    }

    // delete
    fun delete(studentID: Long): Int {
        // Return number of rows affected, so if this is greater than zero
        // the operation is successful
        return writableDatabase.delete(
            StudentTable.TABLE_NAME,
            "${StudentTable.COLUMN_ID} = ?",
            arrayOf(studentID.toString())
        )
    }

    // Update
    fun update(student: Student): Int {
        // Use ContentValues to insert data
        val contentValue = ContentValues().apply {
            put(StudentTable.COLUMN_NAME, student.name)
        }

        // Return number of rows affected, so if this is greater
        // than zero the operation is successful
        return writableDatabase.update(
            StudentTable.TABLE_NAME,
            contentValue,
            "${StudentTable.COLUMN_ID} = ?",
            arrayOf(student.id.toString())
        )
    }*/

}