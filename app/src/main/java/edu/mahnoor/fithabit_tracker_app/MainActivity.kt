package edu.mahnoor.fithabit_tracker_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun startWalkingChallenge(view: View) {
        val intent = Intent(this, activity_daily_steps::class.java)
        startActivity(intent)
    }
}