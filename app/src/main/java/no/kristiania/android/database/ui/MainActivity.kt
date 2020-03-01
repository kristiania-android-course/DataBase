package no.kristiania.android.database.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import no.kristiania.android.database.R
import no.kristiania.android.database.db.StudentDAO

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create Student DAO
        val studentDAO = StudentDAO(this)


        // OnClick listener to button
        btn_save.setOnClickListener {
            // Get value from the edit text (edit_name)
            val name = edit_name.text.toString()
            // Save it to DB
            val id = studentDAO.insert(name)
            // Show what is stored
            Toast.makeText(this, "stored $name in row $id ", Toast.LENGTH_SHORT).show()

        }

        // Fetch list of students

    }


    // https://developer.android.com/reference/android/app/AlertDialog.Builder
}
