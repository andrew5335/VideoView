package com.andrew.videoview.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.andrew.videoview.R
import com.google.android.youtube.player.YouTubeBaseActivity

/**
 * @author Andrew Kim
 * @since 2021. 06. 06
 * @desc VideoView 앱 실행 Splash 화면 Activity
 */
class SplashActivity : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(mainIntent)
            finish()
        }, DURATION)
    }

    companion object {
        private const val DURATION : Long = 2000
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}