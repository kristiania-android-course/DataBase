package no.kristiania.android.database.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import no.kristiania.android.database.db.StudentTable.COLUMN_ID
import no.kristiania.android.database.db.StudentTable.COLUMN_NAME

val DATABASE_VERSION: Int = 1
val DATABASE_NAME: String = "students_database"


open class BaseDataBase(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        val queryCreateStudentTable =
            "CREATE TABLE ${StudentTable.TABLE_NAME} ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT )"
        db?.execSQL(queryCreateStudentTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}