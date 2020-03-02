package no.kristiania.android.database.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import no.kristiania.android.database.R
import no.kristiania.android.database.db.StudentDAO

class MainActivity : AppCompatActivity() {

    // Create Student DAO
    lateinit var studentDAO: StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        studentDAO = StudentDAO(this)

        // OnClick listener to button
        btn_save.setOnClickListener {
            // Get value from the edit text (edit_name)
            val name = edit_name.text.toString()
            // Save it to DBß
            val rowIndex = studentDAO.insert(name)
            // Show what is stored
            Toast.makeText(this, "stored $name in row $rowIndex ", Toast.LENGTH_SHORT).show()

        }

        // fetch data
        val list = studentDAO.fetchingAllRecord()

        // Do your adapter implementation and then show the list in the screen.

        val adapter = StudentAdapter(this, list) { std ->
            Toast.makeText(this, std.name, Toast.LENGTH_SHORT).show()

            Intent(this, StudentDetailsActivity::class.java).apply {
                putExtra("studentID", std.id)
            }.also {
                startActivity(it)
            }

        }
        students_recycler_view.layoutManager = LinearLayoutManager(this)
        students_recycler_view.adapter = adapter




    }
}
