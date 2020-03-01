package no.kristiania.android.database.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_VERSION: Int = 1
val DATABASE_NAME: String = "students_database"


open class BaseDataBase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Create student table query
    val createStudent =
        "CREATE TABLE ${StudentTable.TABLE_NAME} (${StudentTable.COLUMN_ID} INTEGER PRIMARY KEY , ${StudentTable.COLUMN_NAME} TEXT)"

    // Will get called on creation of the database
    override fun onCreate(db: SQLiteDatabase?) {
        // Execute sql query
        db?.execSQL(createStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}