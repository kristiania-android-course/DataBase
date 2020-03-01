package no.kristiania.android.database.ui

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import no.kristiania.android.database.R
import no.kristiania.android.database.StudentDTO
import no.kristiania.android.database.db.StudentDAO

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val studentDb = StudentDAO(this)
        val adapter = StudentsAdapter(this) { studentDTO ->
            showAlertDialogWith(studentDTO)
        }

        // OnClick listener to button
        btn_save.setOnClickListener {
            // Get value from the edit text (edit_name)
            val nameText = edit_name.text.toString()
            // Save it to DB
            val rowId = studentDb.insert(nameText)
            Toast.makeText(this, "Stored $nameText in $rowId", Toast.LENGTH_SHORT).show()

            val list = studentDb.fetchAll()
            adapter.setStudentsList(list)
        }

        students_recycler_view.layoutManager = GridLayoutManager(this, 2)
        students_recycler_view.adapter = adapter

        val list = studentDb.fetchAll()
        adapter.setStudentsList(list)

    }


    // https://developer.android.com/reference/android/app/AlertDialog.Builder
    private fun showAlertDialogWith(studentDTO: StudentDTO) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("What do you wanna do?")
        builder.setNegativeButton("DELETE", DialogInterface.OnClickListener { dialog, which ->
            // Delete the row
        })

        builder.setPositiveButton("Update", DialogInterface.OnClickListener { dialog, which ->
            // Update the row
        })

        val alertDialog = builder.create()
        alertDialog.show()
    }
}
