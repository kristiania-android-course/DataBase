package no.kristiania.android.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import no.kristiania.android.database.Student

val DATABASE_NAME: String = "students_database"

@Database(entities = arrayOf(Student::class), version = 1)
abstract class BaseDataBase : RoomDatabase() {

    abstract fun studentDAO(): StudentDAO

    companion object {
        var db: BaseDataBase? = null

        fun getDB(context: Context): BaseDataBase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    BaseDataBase::class.java, DATABASE_NAME
                ).build()
            }
            return db!!
        }
    }
}