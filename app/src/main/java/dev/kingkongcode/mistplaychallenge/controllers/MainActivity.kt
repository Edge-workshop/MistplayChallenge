package dev.kingkongcode.mistplaychallenge.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.animation.AnimationUtils
import dev.kingkongcode.mistplaychallenge.R
import dev.kingkongcode.mistplaychallenge.databinding.ActivityMainBinding

/**
 * Splash screen with company logo
 * **/
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var countDownTimer: CountDownTimer

    private companion object {
        private const val INITIAL_COUNTDOWN_TIME: Long = 850
        private const val COUNTDOWN_INTERVAL: Long = 1000
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        scaleAnimationOnTitle()
        automaticIntentTransition()
    }

    private fun scaleAnimationOnTitle() {
        //scale animation on title
        val scale = AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale_up)
        binding.tvChallengeTitle.startAnimation(scale)
    }
    private fun automaticIntentTransition() {
        //Code section to start timer to go automatically in HomeGamesCategoriesActivity
        countDownTimer = object : CountDownTimer(INITIAL_COUNTDOWN_TIME, COUNTDOWN_INTERVAL) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                Log.i(TAG, "CountDownTimer onFinish is called, going to HomeGamesCategoriesActivity")
                val intent = Intent(this@MainActivity, HomeGamesCategoriesActivity::class.java)
                startActivity(intent)
                //I did this for a smooth transition between activity
                overridePendingTransition(R.anim.smooth_activity_transition_enter, R.anim.smooth_activity_transition_exit)
                finish()
            }
        }

        countDownTimer.start()
    }
}