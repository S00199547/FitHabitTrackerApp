package edu.mahnoor.fithabit_tracker_app

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.content.SharedPreferences




class MainActivity : AppCompatActivity() {

    private lateinit var challengeNameTextView: TextView
    private lateinit var stepsTextView: TextView
    private lateinit var progressTextView: TextView
    private lateinit var btnViewChallenges: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Find views by ID
        challengeNameTextView = findViewById(R.id.tvChallengeName)
        stepsTextView = findViewById(R.id.tvSteps)
        progressTextView = findViewById(R.id.tvProgress)
        btnViewChallenges = findViewById(R.id.btnViewChallenges)

        // Retrieve and display challenge data
        val challenge = getFitnessChallenge()
        updateUI(challenge)

        // Button click to view challenges
        btnViewChallenges.setOnClickListener {
            // Start ChallengeListActivity with startActivityForResult
            startActivityForResult(Intent(this, FitnessChallengesActivity::class.java), REQUEST_CODE_UPDATE_DASHBOARD)
        }
    }

    private fun getFitnessChallenge(): fitnesschallenges {
        return fitnesschallenges("Walking Challenge", getStoredSteps("Walking Challenge"), 30)
    }

    private fun updateUI(challenge: fitnesschallenges) {
        // Update UI based on challenge data
        challengeNameTextView.text = challenge.name
        stepsTextView.text = "Steps: ${challenge.steps}"
        progressTextView.text = "Progress: ${calculateProgress(challenge.steps, challenge.duration)}%"
    }

    private fun calculateProgress(steps: Int, duration: Int): Int {
        // Calculate progress percentage based on the number of steps and duration
        return if (duration > 0) {
            (steps.toDouble() / (duration * TARGET_STEPS_PER_DAY.toDouble()) * 100).toInt()
        } else {
            0
        }
    }

    private fun getStoredSteps(challengeName: String): Int {
        // Retrieve stored steps from SharedPreferences
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(challengeName, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_UPDATE_DASHBOARD && resultCode == Activity.RESULT_OK) {
            // Check if the result contains the updated challenge
            val updatedChallenge = data?.getParcelableExtra<fitnesschallenges>("updatedChallenge")
            if (updatedChallenge != null) {
                // Update UI based on the updated challenge
                updateUI(updatedChallenge)
            }
        }
    }


    companion object {
        private const val TARGET_STEPS_PER_DAY = 10000
        const val PREFS_NAME = "FitnessPrefs"
        private const val REQUEST_CODE_UPDATE_DASHBOARD = 1
    }
}