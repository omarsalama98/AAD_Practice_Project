package com.example.o.aad_practice_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.o.aad_practice_project.R
import com.example.o.aad_practice_project.adapters.SkillIqLeadersListAdapter
import com.example.o.aad_practice_project.model.SkillIqLearner
import com.example.o.aad_practice_project.repository.API
import com.example.o.aad_practice_project.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SkillIqLeadersFragment : Fragment() {

    private val skillIqList: ArrayList<SkillIqLearner> = ArrayList()
    private var fetchedRequests = false
    private val api = RetrofitClient.getInstance().getAPI(API::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.skill_iq_leaders_progress_bar)
        val mLayoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        val mRecyclerView = view.findViewById<RecyclerView>(R.id.skill_iq_leaders_recycler_view)
        val mAdapter = SkillIqLeadersListAdapter(skillIqList)

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = mLayoutManager

        if (!fetchedRequests) {
            val mCall: Call<List<SkillIqLearner>> = api.skillIq
            mCall.enqueue(object : Callback<List<SkillIqLearner>> {
                override fun onResponse(
                    call: Call<List<SkillIqLearner>>?,
                    response: Response<List<SkillIqLearner>>?
                ) {
                    if (response != null) {
                        skillIqList.addAll(response.body())
                        mAdapter.notifyDataSetChanged()
                        progressBar.visibility = View.GONE
                        fetchedRequests = true
                    }
                }

                override fun onFailure(call: Call<List<SkillIqLearner>>?, t: Throwable?) {
                }
            })
        } else {
            progressBar.visibility = View.GONE
        }
    }

}