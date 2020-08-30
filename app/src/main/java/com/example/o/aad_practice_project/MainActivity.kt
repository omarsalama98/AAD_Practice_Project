package com.example.o.aad_practice_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.o.aad_practice_project.adapters.ViewPagerAdapter
import com.example.o.aad_practice_project.fragments.LearningLeadersFragment
import com.example.o.aad_practice_project.fragments.SkillIqLeadersFragment
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton = findViewById<Button>(R.id.submit_button)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        val adapter = ViewPagerAdapter(supportFragmentManager)

        val learningLeadersFragment = LearningLeadersFragment()
        val skillIqLeadersFragment = SkillIqLeadersFragment()
        adapter.addFragment(learningLeadersFragment, "Learning Leaders")
        adapter.addFragment(skillIqLeadersFragment, "Skill IQ Leaders")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        submitButton.setOnClickListener {
            startActivity(Intent(this, SubmissionActivity::class.java))
        }
    }
}