package com.user.brayan.condorlabs.ui.welcome


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.user.brayan.condorlabs.R
import com.user.brayan.condorlabs.ui.main.MainActivity


class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    override fun onResume() {
        super.onResume()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this@Welcome, MainActivity::class.java)
                this@Welcome.startActivity(intent)
                finish()
            }, 2000
        )
    }
}