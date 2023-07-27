package com.example.swipedemo.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.swipedemo.R
import com.example.swipedemo.databinding.ActivitySplashBinding
import com.example.swipedemo.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToHomeScreen()
    }

    private fun goToHomeScreen() {
        handler.postDelayed({
            val homeIntent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        }, 3000)
    }
}