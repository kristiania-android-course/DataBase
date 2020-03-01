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
        val studentID = intent.getLongExtra("studentID", 0)

        val student = studentDAO.getRecordWithID(studentID)
        edit_details_name.setText(student.name)

        delete.setOnClickListener {
            studentDAO.delete(studentID)
            onBackPressed()
        }

        update.setOnClickListener {
            studentDAO.update(Student(studentID, edit_details_name.text.toString()))
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        studentDAO.close()
    }
}
