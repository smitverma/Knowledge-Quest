package com.sevenbitsinabyte.knowledgequest

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial

private var activityState = "home"
private val slowerAnimMS = 300L
private val fasterAnimMS = 220L
private var mediaPlayer: MediaPlayer? = null

class QuizStarterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_starter)

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic_home)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        }

        window.statusBarColor = resources.getColor(R.color.black)


        val homeAppLogo = findViewById<ImageView>(R.id.home_appLogo)
        val subjectsGrid = findViewById<GridLayout>(R.id.starter_subjectsGrid)
        val optionsGrid = findViewById<GridLayout>(R.id.home_optionsGrid)
        val labelInfo = findViewById<TextView>(R.id.starter_labelInfo)
        val brandingGrid = findViewById<GridLayout>(R.id.home_labelBranding)
        val settingsBackground = findViewById<ImageView>(R.id.home_settingsBackground)
        val settingsFrame = findViewById<FrameLayout>(R.id.settingsContainerFrame)
        val musicSwitch = findViewById<SwitchMaterial>(R.id.settings_musicSwitch)
        val soundsSwitch = findViewById<SwitchMaterial>(R.id.settings_soundsSwitch)

        subjectsGrid.alpha = 0F
        labelInfo.alpha = 0F
        subjectsGrid.visibility = View.INVISIBLE
        labelInfo.visibility = View.INVISIBLE
        labelInfo.animate().translationXBy(500F).setDuration(1).start()

        settingsFrame.visibility = View.INVISIBLE

        musicSwitch.isChecked = true
        soundsSwitch.isChecked = true

        findViewById<MaterialButton>(R.id.home_classicButton).setOnClickListener {

            if (activityState != "home")
                return@setOnClickListener

            activityState = "classic"

//            homeAppLogo.animate().alphaBy(-1F).setDuration(500).start()
//            homeAppLogo.animate().translationYBy(-75F).setDuration(slowerAnimMS).start()
//            homeAppLogo.animate().scaleXBy(-0.4F).setDuration(slowerAnimMS).start()
//            homeAppLogo.animate().scaleYBy(-0.4F).setDuration(slowerAnimMS).start()
            optionsGrid.animate().alphaBy(-1F).setDuration(slowerAnimMS).start()
            brandingGrid.animate().alphaBy(-1F).setDuration(slowerAnimMS).start()
            brandingGrid.animate().translationXBy(-500F).setDuration(slowerAnimMS).start()
//            starterAppLogo.visibility = View.VISIBLE
            subjectsGrid.visibility = View.VISIBLE
            labelInfo.visibility = View.VISIBLE
            subjectsGrid.animate().alphaBy(1F).setDuration(slowerAnimMS).start()
            labelInfo.animate().alphaBy(1F).setDuration(slowerAnimMS).start()
            labelInfo.animate().translationXBy(-500F).setDuration(slowerAnimMS).start()

        }

        findViewById<MaterialButton>(R.id.home_blitzButton).setOnClickListener {

            if (activityState != "home")
                return@setOnClickListener

            activityState = "blitz"

//            homeAppLogo.animate().alphaBy(-1F).setDuration(500).start()
//            homeAppLogo.animate().translationYBy(-75F).setDuration(slowerAnimMS).start()
//            homeAppLogo.animate().scaleXBy(-0.4F).setDuration(slowerAnimMS).start()
//            homeAppLogo.animate().scaleYBy(-0.4F).setDuration(slowerAnimMS).start()
            optionsGrid.animate().alphaBy(-1F).setDuration(slowerAnimMS).start()
            brandingGrid.animate().alphaBy(-1F).setDuration(slowerAnimMS).start()
//            starterAppLogo.visibility = View.VISIBLE
            subjectsGrid.visibility = View.VISIBLE
            labelInfo.visibility = View.VISIBLE
            subjectsGrid.animate().alphaBy(1F).setDuration(slowerAnimMS).start()
            labelInfo.animate().alphaBy(1F).setDuration(slowerAnimMS).start()

        }

        findViewById<MaterialButton>(R.id.home_settingsButton).setOnClickListener {

            if (activityState != "home")
                return@setOnClickListener

            settingsFrame.visibility = View.VISIBLE
            activityState = "settings"
        }

        findViewById<MaterialButton>(R.id.starter_geoButton).setOnClickListener {
            val intent = Intent(this, QuizRunningActivity::class.java)
            intent.putExtra("theme", "geo")
            startActivity(intent)
        }

        findViewById<MaterialButton>(R.id.starter_chemButton).setOnClickListener {
            val intent = Intent(this, QuizRunningActivity::class.java)
            intent.putExtra("theme", "chem")
            startActivity(intent)
        }

        findViewById<MaterialButton>(R.id.starter_phyButton).setOnClickListener {
            val intent = Intent(this, QuizRunningActivity::class.java)
            intent.putExtra("theme", "phy")
            startActivity(intent)
        }

        findViewById<MaterialButton>(R.id.starter_astroButton).setOnClickListener {
            val intent = Intent(this, QuizRunningActivity::class.java)
            intent.putExtra("theme", "astro")
            startActivity(intent)
        }

        settingsBackground.setOnClickListener {

            activityState = "home"

            settingsFrame.visibility = View.INVISIBLE
        }

        findViewById<SwitchMaterial>(R.id.settings_musicSwitch).setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (mediaPlayer?.isPlaying != true) {
                    mediaPlayer?.start()
                }
            } else {
                mediaPlayer?.pause()
            }
        }

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (activityState == "home") {

                } else if (activityState == "classic" || activityState == "blitz") {

                    activityState = "home"

//                    homeAppLogo.animate().translationYBy(75F).setDuration(fasterAnimMS).start()
//                    homeAppLogo.animate().scaleXBy(0.4F).setDuration(fasterAnimMS).start()
//                    homeAppLogo.animate().scaleYBy(0.4F).setDuration(fasterAnimMS).start()
                    optionsGrid.animate().alphaBy(1F).setDuration(fasterAnimMS).start()
                    brandingGrid.animate().alphaBy(1F).setDuration(fasterAnimMS).start()
                    brandingGrid.animate().translationXBy(500F).setDuration(slowerAnimMS).start()
                    subjectsGrid.animate().alphaBy(-1F).setDuration(fasterAnimMS).start()
                    labelInfo.animate().alphaBy(-1F).setDuration(fasterAnimMS).start()
                    labelInfo.animate().translationXBy(500F).setDuration(slowerAnimMS).start()

                    Handler(Looper.getMainLooper()).postDelayed({
                        subjectsGrid.visibility = View.INVISIBLE
                        labelInfo.visibility = View.INVISIBLE
                    }, 500)
                } else if (activityState == "settings") {
                    activityState = "home"
                    settingsFrame.visibility = View.INVISIBLE
                }
//                if (shouldNotInvokeAgain) {
//                    this.isEnabled = false
//                }
            }
        })
    }

    override fun onDestroy() {
        mediaPlayer?.release()

        super.onDestroy()
    }
}