package com.example.o.aad_practice_project

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat

object AlertDialogHelper {

    const val ON_SUCCESS = "Success"
    const val ON_FAILURE = "Failure"
    const val ON_SUBMITTING = "Submitting"

    fun setupAlertDialog(
        layoutInflater: LayoutInflater,
        mAlertMessage: AlertDialog,
        context: Context,
        responseType: String
    ) {
        val messageView = layoutInflater.inflate(R.layout.alert_message, null)
        mAlertMessage.setView(messageView)
        mAlertMessage.window?.setBackgroundDrawable(
            ResourcesCompat.getDrawable(
                context.resources,
                R.drawable.cornered_button,
                null
            )
        )
        var responseMessage = ""
        var responseIcon: Drawable? = null
        when (responseType) {
            ON_SUCCESS -> {
                responseMessage = context.getString(R.string.submission_successful)
                responseIcon = ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.successful_icon,
                    null
                )
            }
            ON_FAILURE -> {
                responseMessage = context.getString(R.string.submission_failed)
                responseIcon = ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.failed_icon,
                    null
                )
            }
            ON_SUBMITTING -> {
                responseMessage = context.getString(R.string.submitting)
                responseIcon = ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.gads_logo,
                    null
                )
            }
        }
        messageView.findViewById<TextView>(R.id.submission_response_message)?.text = responseMessage
        messageView.findViewById<ImageView>(R.id.submission_response_image)
            ?.setImageDrawable(responseIcon)
    }
}