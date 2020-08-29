package com.example.o.aad_practice_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val root = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false)

        val mLayoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        val mRecyclerView = root.findViewById<RecyclerView>(R.id.skill_iq_leaders_recycler_view)
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
                        fetchedRequests = true
                    }
                }

                override fun onFailure(call: Call<List<SkillIqLearner>>?, t: Throwable?) {
                }
            })
        }

        return root
    }

}