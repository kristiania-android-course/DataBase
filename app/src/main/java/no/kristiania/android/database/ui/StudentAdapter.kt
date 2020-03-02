package no.kristiania.android.database.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_item.view.*
import no.kristiania.android.database.R
import no.kristiania.android.database.Student


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 02 March 2020
 */
class StudentAdapter(
    val context: Context,
    val studentList: List<Student>,
    val function: (Student) -> Unit
) :
    RecyclerView.Adapter<StudentAdapter.StudentHolder>() {


    class StudentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.student_item, null)
        val holder = StudentHolder(view)


        holder.itemView.setOnClickListener {
            val item = studentList[holder.adapterPosition]
            function(item)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.itemView.student_name.setText(studentList[position].name)
        holder.itemView.student_id.setText(studentList[position].id.toString())
    }
}