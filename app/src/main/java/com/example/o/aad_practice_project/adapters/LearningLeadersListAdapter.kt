package com.example.o.aad_practice_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.o.aad_practice_project.R
import com.example.o.aad_practice_project.model.Learner

class LearningLeadersListAdapter(private val learners: ArrayList<Learner>) :
    RecyclerView.Adapter<LearningLeadersListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.learning_leaders_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = learners[position].name
        val mDetails: String =
            learners[position].hours.toString() + " learning hours, " + learners[position].country
        holder.details.text = mDetails

    }

    override fun getItemCount(): Int {
        return learners.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var name: TextView = v.findViewById(R.id.learning_leaders_list_item_name)
        var details: TextView = v.findViewById(R.id.learning_leaders_list_item_details)
    }
}