package no.kristiania.android.database.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_student_details.*
import no.kristiania.android.database.R
import no.kristiania.android.database.Student
import no.kristiania.android.database.db.StudentDAO

class StudentDetailsActivity : AppCompatActivity() {

    val studentDAO = StudentDAO(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        // get student id from extras
        val studentID = intent.getLongExtra("studentID", 0)

        // Get student record from db using student id and set to edit-text
        val student = studentDAO.getRecordWithID(studentID)
        edit_details_name.setText(student.name)

        delete.setOnClickListener {
            //Delete student data
            studentDAO.delete(studentID)
            onBackPressed()
        }

        update.setOnClickListener {
            // Update the student data
            studentDAO.update(Student(studentID, edit_details_name.text.toString()))
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //Close db after use
        studentDAO.close()
    }
}
