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
import com.example.o.aad_practice_project.repository.SubmissionAPI
import com.example.o.aad_practice_project.repository.SubmissionRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmissionActivity : AppCompatActivity() {

    private val api = SubmissionRetrofitClient.getInstance().getAPI(SubmissionAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission)

        val firstNameEditText = findViewById<EditText>(R.id.first_name_edit_text)
        val lastNameEditText = findViewById<EditText>(R.id.last_name_edit_text)
        val emailAddressEditText = findViewById<EditText>(R.id.email_address_edit_text)
        val githubLinkEditText = findViewById<EditText>(R.id.github_link_edit_text)
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

                val mLoadingAlertMessage = AlertDialog.Builder(this).create()
                val mMessageView = layoutInflater.inflate(R.layout.alert_message, null)
                mLoadingAlertMessage.setView(mMessageView)
                mLoadingAlertMessage.window?.setBackgroundDrawable(
                    ResourcesCompat.getDrawable(resources, R.drawable.cornered_button, null)
                )
                mMessageView.findViewById<TextView>(R.id.submission_response_message)?.text =
                    getString(R.string.submitting)
                mMessageView.findViewById<ImageView>(R.id.submission_response_image)
                    ?.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.gads_logo,
                            null
                        )
                    )
                mLoadingAlertMessage.show()

                val mCall: Call<Void> = api.submit(
                    emailAddressEditText.text.toString(), firstNameEditText.text.toString(),
                    lastNameEditText.text.toString(), githubLinkEditText.text.toString()
                )

                mCall.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        if (response != null) {
                            if (mLoadingAlertMessage.isShowing)
                                mLoadingAlertMessage.dismiss()
                            val mAlertMessage =
                                AlertDialog.Builder(this@SubmissionActivity).create()
                            val messageView = layoutInflater.inflate(R.layout.alert_message, null)
                            mAlertMessage.setView(messageView)
                            mAlertMessage.window?.setBackgroundDrawable(
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.cornered_button,
                                    null
                                )
                            )
                            if (response.isSuccessful) {
                                messageView.findViewById<TextView>(R.id.submission_response_message)?.text =
                                    getString(R.string.submission_successful)
                                messageView.findViewById<ImageView>(R.id.submission_response_image)
                                    ?.setImageDrawable(
                                        ResourcesCompat.getDrawable(
                                            resources,
                                            R.drawable.successful_icon,
                                            null
                                        )
                                    )
                                mAlertMessage.show()
                                Handler().postDelayed({
                                    mAlertMessage.dismiss()
                                    finish()
                                }, 3000)
                            } else {
                                messageView.findViewById<TextView>(R.id.submission_response_message)?.text =
                                    getString(R.string.submission_failed)
                                messageView.findViewById<ImageView>(R.id.submission_response_image)
                                    ?.setImageDrawable(
                                        ResourcesCompat.getDrawable(
                                            resources,
                                            R.drawable.failed_icon,
                                            null
                                        )
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
                        val messageView = layoutInflater.inflate(R.layout.alert_message, null)
                        mAlertMessage.setView(messageView)
                        mAlertMessage.window?.setBackgroundDrawable(
                            ResourcesCompat.getDrawable(resources, R.drawable.cornered_button, null)
                        )
                        messageView.findViewById<TextView>(R.id.submission_response_message)?.text =
                            getString(R.string.submission_failed)
                        messageView.findViewById<ImageView>(R.id.submission_response_image)
                            ?.setImageDrawable(
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.failed_icon,
                                    null
                                )
                            )
                        mAlertMessage.show()
                        Handler().postDelayed({
                            mAlertMessage.dismiss()
                        }, 3000)
                    }

                })
            }
            mAlert.show()
        }
    }

}