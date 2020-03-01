package no.kristiania.android.database.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import no.kristiania.android.database.R

class MainActivity : AppCompatActivity() {

    // Create Student DAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // OnClick listener to button
        btn_save.setOnClickListener {
            // Get value from the edit text (edit_name)

            // Save it to DB
            // Show what is stored
            //Toast.makeText(this, "stored $name in row $id ", Toast.LENGTH_SHORT).show()

        }

        // fetch data

        // Do your adapter implementation and then show the list in the screen.



    }
}
