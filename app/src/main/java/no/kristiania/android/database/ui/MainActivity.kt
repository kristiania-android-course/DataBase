package no.kristiania.android.database.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import no.kristiania.android.database.R
import no.kristiania.android.database.Student
import no.kristiania.android.database.db.BaseDataBase
import no.kristiania.android.database.db.StudentDAO


class MainActivity : AppCompatActivity() {

    // Create Student DAO
    lateinit var studentDAO: StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentDAO = BaseDataBase.getDB(this).studentDAO()

        // OnClick listener to button
        btn_save.setOnClickListener {
            // Get value from the edit text (edit_name)
            val name = edit_name.text.toString()

            // Save it to DB in a new thread
            Thread(Runnable {
                studentDAO.insert(Student(0, name))
                // Show what is stored
                runOnUiThread {
                    Toast.makeText(this, "stored $name ", Toast.LENGTH_SHORT).show()
                    updateList()
                }

            }).start()


        }


    }

    override fun onResume() {
        super.onResume()

        updateList()



        // Do your adapter implementation and then show the list in the screen.

    }

    fun updateList() {
        Thread(Runnable {
            val list = studentDAO.fetchAllRecord()
            for (item in list) {
                Log.d("MainActivity", "Id is ${item.id} and name is ${item.name}")
            }

            runOnUiThread {
                val adapter = StudentAdapter(this, list) {
                    Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
                    // Intent to open new activity and pass student id
                    val intent = Intent(this, StudentDetailsActivity::class.java)
                    intent.putExtra("studentID", it.id)
                    startActivity(intent)
                }
                students_recycler_view.layoutManager = GridLayoutManager(this, 1)
                students_recycler_view.adapter = adapter
            }
        }).start()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
