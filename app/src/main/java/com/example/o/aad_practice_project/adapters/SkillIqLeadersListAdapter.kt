package com.example.o.aad_practice_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.o.aad_practice_project.R
import com.example.o.aad_practice_project.model.SkillIqLearner
import com.squareup.picasso.Picasso

class SkillIqLeadersListAdapter(private val learners: ArrayList<SkillIqLearner>) :
    RecyclerView.Adapter<SkillIqLeadersListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.skill_iq_leaders_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = learners[position].name
        val mDetails: String =
            learners[position].score.toString() + " skill IQ Score, " + learners[position].country
        holder.details.text = mDetails
        Picasso.get().load(learners[position].badgeUrl).placeholder(R.drawable.skill_iq_trimmed)
            .into(holder.badge)
    }

    override fun getItemCount(): Int {
        return learners.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var name: TextView = v.findViewById(R.id.skill_iq_leaders_list_item_name)
        var details: TextView = v.findViewById(R.id.skill_iq_leaders_list_item_details)
        var badge: ImageView = v.findViewById(R.id.skill_iq_leaders_list_item_image)
    }
}