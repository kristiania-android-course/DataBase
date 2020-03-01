package no.kristiania.android.database.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_item.view.*
import no.kristiania.android.database.R
import no.kristiania.android.database.StudentDTO

class StudentsAdapter(private val context: Context, val lambdaFunction: (StudentDTO) -> Unit) :
    RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {

    val studentsList = mutableListOf<StudentDTO>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val student_name = itemView.student_name
        val id = itemView.student_id

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.student_item, null)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            lambdaFunction(studentsList[holder.adapterPosition])
        }
        return holder
    }

    override fun getItemCount(): Int {
        return studentsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.student_name.text = studentsList[position].name
        holder.id.text = studentsList[position].id.toString()
    }

    fun setStudentsList(list: List<StudentDTO>) {
        studentsList.clear()
        studentsList.addAll(list)
        notifyDataSetChanged()
    }

}
