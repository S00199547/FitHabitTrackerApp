package edu.mahnoor.fithabit_tracker_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class activity_daily_steps : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_steps)
    }

    fun saveDailySteps(view: View) {
        // Implement logic to save daily steps
        // You can use SharedPreferences, a database, or send data to a backend server
    }
}