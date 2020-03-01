package no.kristiania.android.database.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_student_details.*
import no.kristiania.android.database.R
import no.kristiania.android.database.Student
import no.kristiania.android.database.db.BaseDataBase
import no.kristiania.android.database.db.StudentDAO

class StudentDetailsActivity : AppCompatActivity() {

    // Create Student DAO
    lateinit var studentDAO: StudentDAO
    lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentDAO = BaseDataBase.getDB(this).studentDAO()

        // get student id from extras
        val studentID = intent.getLongExtra("studentID", 0)

        // Get student record from db using student id and set to edit-text

        Thread(Runnable {
            student = studentDAO.getRecordWithID(studentID)

            //update in main thread
            runOnUiThread {
                edit_details_name.setText(student.name)
            }
        }).start()


        delete.setOnClickListener {
            //Delete student data
            Thread(Runnable {
                studentDAO.delete(student)
                //Go back in main thread
                runOnUiThread {
                    onBackPressed()
                }

            }).start()
        }

        update.setOnClickListener {
            // Update the student data
            Thread(Runnable {
                studentDAO.update(Student(studentID, edit_details_name.text.toString()))
                //Go back in main thread
                runOnUiThread {
                    onBackPressed()
                }

            }).start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
