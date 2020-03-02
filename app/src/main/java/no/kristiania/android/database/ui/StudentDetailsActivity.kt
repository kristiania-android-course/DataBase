package no.kristiania.android.database.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_student_details.*
import no.kristiania.android.database.R
import no.kristiania.android.database.Student
import no.kristiania.android.database.db.StudentDAO

class StudentDetailsActivity : AppCompatActivity() {

    // Create Student DAO

    lateinit var studentDAO: StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        studentDAO = StudentDAO(this)
        // get student id from extras

        val id = intent.getLongExtra("studentID", 0)

        val student = studentDAO.getStudentWithID(id)
        // Get student record from db using student id and set to edit-text
        edit_details_name.setText(student.name)

        delete.setOnClickListener {
            //Delete student data
            studentDAO.delete(id)
            onBackPressed()
        }

        update.setOnClickListener {
            // Update the student data
            val newName = edit_details_name.text.toString()
            studentDAO.update(Student(id, newName))
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
