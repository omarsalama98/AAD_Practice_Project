package com.example.o.aad_practice_project

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SubmissionActivity : AppCompatActivity() {

    private var firstNameEditText: EditText? = null
    private var lastNameEditText: EditText? = null
    private var emailAddressEditText: EditText? = null
    private var githubLinkEditText: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission)

        firstNameEditText = findViewById<EditText>(R.id.first_name_edit_text)
        lastNameEditText = findViewById<EditText>(R.id.last_name_edit_text)
        emailAddressEditText = findViewById<EditText>(R.id.email_address_edit_text)
        githubLinkEditText = findViewById<EditText>(R.id.github_link_edit_text)
        val submitButton = findViewById<Button>(R.id.submission_submit_button)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        submitButton.setOnClickListener {

            val mAlert = AlertDialog.Builder(this).create()
            mAlert.setIcon(R.drawable.successfull_icon)
            mAlert.setTitle("Are you sure?")
            mAlert.show()
        }

    }

    private fun checkEmptyEntry(): Boolean {
        if (firstNameEditText?.text?.isEmpty()!! ||
            lastNameEditText?.text?.isEmpty()!! ||
            githubLinkEditText?.text?.isEmpty()!! ||
            emailAddressEditText?.text?.isEmpty()!!
        ) {
            return true
        }
        return false
    }
}