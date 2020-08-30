package com.example.o.aad_practice_project

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat

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
            val mView = layoutInflater.inflate(R.layout.alert_dialog, null)
            mAlert.setView(mView)
            mAlert.setCancelable(true)
            mAlert.window?.setBackgroundDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.cornered_button, null)
            )

            mView.findViewById<TextView>(R.id.alert_message)?.text =
                getString(R.string.are_you_sure_msg)
            mView.findViewById<ImageView>(R.id.submission_cancel_button)?.setOnClickListener {
                mAlert.dismiss()
            }
            mView.findViewById<Button>(R.id.submission_yes_button)?.setOnClickListener {
                mAlert.dismiss()
                val mAlertMessage = AlertDialog.Builder(this).create()
                val messageView = layoutInflater.inflate(R.layout.alert_message, null)
                mAlertMessage.setView(messageView)
                mAlertMessage.window?.setBackgroundDrawable(
                    ResourcesCompat.getDrawable(resources, R.drawable.cornered_button, null)
                )

                messageView.findViewById<TextView>(R.id.submission_response_message)?.text =
                    getString(R.string.submission_successful)
                messageView.findViewById<ImageView>(R.id.submission_response_image)
                    ?.setImageDrawable(
                        ResourcesCompat.getDrawable(resources, R.drawable.successful_icon, null)
                    )
                mAlertMessage.show()
                Handler().postDelayed({
                    mAlertMessage.dismiss()
                    finish()
                }, 3000)
            }
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