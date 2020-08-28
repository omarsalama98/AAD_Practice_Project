package com.example.o.aad_practice_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val goToMainActivity = Intent(this, MainActivity::class.java)
        val mRunnable = Runnable {
            startActivity(goToMainActivity)
            finish()
        }
        Handler().postDelayed(mRunnable, 2000)
    }
}