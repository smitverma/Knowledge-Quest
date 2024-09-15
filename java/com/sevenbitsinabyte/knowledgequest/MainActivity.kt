package com.sevenbitsinabyte.knowledgequest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setOnExitAnimationListener {

            val intent = Intent(this, QuizStarterActivity::class.java)
            startActivity(intent)
        }

        setContentView(R.layout.activity_main)

    }
}