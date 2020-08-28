package com.example.o.aad_practice_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.o.aad_practice_project.model.Learner
import com.example.o.aad_practice_project.repository.API
import com.example.o.aad_practice_project.repository.RetrofitClient

class SkillIqLeaders : Fragment() {

    private val skillIqList: ArrayList<Learner> = ArrayList()
    private var fetchedRequests = false
    private val api = RetrofitClient.getInstance().getAPI(API::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false)
        

        return root
    }

}