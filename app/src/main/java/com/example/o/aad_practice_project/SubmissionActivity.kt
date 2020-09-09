package com.example.o.aad_practice_project

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.o.aad_practice_project.repository.RetrofitClient
import com.example.o.aad_practice_project.repository.SubmissionAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmissionActivity : AppCompatActivity() {

    private val api = RetrofitClient.getInstance().getAPI(SubmissionAPI::class.java)
    private val submissionUrl =
        "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission)

        val firstNameEditText = findViewById<EditText>(R.id.first_name_edit_text)
        val lastNameEditText = findViewById<EditText>(R.id.last_name_edit_text)
        val emailAddressEditText = findViewById<EditText>(R.id.email_address_edit_text)
        val githubLinkEditText = findViewById<EditText>(R.id.github_link_edit_text)
        val submitButton = findViewById<Button>(R.id.submission_submit_button)
        findViewById<ImageView>(R.id.submission_back_arrow).setOnClickListener {
            onBackPressed()
        }

        submitButton.setOnClickListener {

            val confirmationAlertDialog = AlertDialog.Builder(this).create()
            val confirmationView = layoutInflater.inflate(R.layout.alert_dialog, null)
            confirmationAlertDialog.setView(confirmationView)
            confirmationAlertDialog.setCancelable(true)
            confirmationAlertDialog.window?.setBackgroundDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.cornered_button, null)
            )
            confirmationView.findViewById<TextView>(R.id.alert_message)?.text =
                getString(R.string.are_you_sure_msg)
            confirmationView.findViewById<ImageView>(R.id.submission_cancel_button)
                ?.setOnClickListener {
                    confirmationAlertDialog.dismiss()
                }

            confirmationView.findViewById<Button>(R.id.submission_yes_button)?.setOnClickListener {
                confirmationAlertDialog.dismiss()
                val submittingAlertMessage = AlertDialog.Builder(this).create()
                AlertDialogHelper.setupAlertDialog(
                    layoutInflater, submittingAlertMessage,
                    this, AlertDialogHelper.ON_SUBMITTING
                )
                submittingAlertMessage.show()
                val mCall: Call<Void> = api.submit(
                    submissionUrl,
                    emailAddressEditText.text.toString(), firstNameEditText.text.toString(),
                    lastNameEditText.text.toString(), githubLinkEditText.text.toString()
                )

                mCall.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        if (response != null) {
                            if (submittingAlertMessage.isShowing)
                                submittingAlertMessage.dismiss()
                            val mAlertMessage =
                                AlertDialog.Builder(this@SubmissionActivity).create()
                            if (response.isSuccessful) {
                                AlertDialogHelper.setupAlertDialog(
                                    layoutInflater, mAlertMessage,
                                    this@SubmissionActivity, AlertDialogHelper.ON_SUCCESS
                                )
                                mAlertMessage.show()
                                Handler().postDelayed({
                                    mAlertMessage.dismiss()
                                    finish()
                                }, 3000)
                            } else {
                                AlertDialogHelper.setupAlertDialog(
                                    layoutInflater, mAlertMessage,
                                    this@SubmissionActivity, AlertDialogHelper.ON_FAILURE
                                )
                                mAlertMessage.show()
                                Handler().postDelayed({
                                    mAlertMessage.dismiss()
                                }, 3000)
                            }
                        }
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                        val mAlertMessage = AlertDialog.Builder(this@SubmissionActivity).create()
                        AlertDialogHelper.setupAlertDialog(
                            layoutInflater, mAlertMessage,
                            this@SubmissionActivity, AlertDialogHelper.ON_FAILURE
                        )
                        mAlertMessage.show()
                        Handler().postDelayed({
                            mAlertMessage.dismiss()
                        }, 3000)
                    }
                })
            }
            confirmationAlertDialog.show()
        }
    }

}