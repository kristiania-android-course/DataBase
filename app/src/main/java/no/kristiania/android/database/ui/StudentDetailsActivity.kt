package no.kristiania.android.database.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_student_details.*
import no.kristiania.android.database.R

class StudentDetailsActivity : AppCompatActivity() {

    // Create Student DAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        // get student id from extras

        val id = intent.getLongExtra("studentID", 0)

        // Get student record from db using student id and set to edit-text


        delete.setOnClickListener {
            //Delete student data
        }

        update.setOnClickListener {
            // Update the student data
        }
    }
}
