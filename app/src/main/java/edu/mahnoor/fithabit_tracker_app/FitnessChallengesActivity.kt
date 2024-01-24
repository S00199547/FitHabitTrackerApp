package edu.mahnoor.fithabit_tracker_app

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.EditText



class FitnessChallengesActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var btnAddSteps: Button
    private lateinit var btnUpdateStepsList: Button
    private lateinit var editTextSteps: EditText

    private lateinit var challengeList: List<fitnesschallenges>
    private var selectedChallengePosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitness_challenges)


        // Find views by ID
        listView = findViewById(R.id.listViewChallenges)
        btnAddSteps = findViewById(R.id.btnAddSteps)
        btnUpdateStepsList = findViewById(R.id.btnUpdateStepsList)
        editTextSteps = findViewById(R.id.editTextSteps)

        // Set up ListView with an adapter
        challengeList = getChallengeList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, challengeList.map { it.name })
        listView.adapter = adapter

        // Handle item click to update steps
        listView.setOnItemClickListener { _, _, position, _ ->
            selectedChallengePosition = position
            // Update UI or show a dialog indicating the selected challenge
            Toast.makeText(this, "Selected Challenge: ${challengeList[position].name}", Toast.LENGTH_SHORT).show()
        }

        // Button click to add steps
        btnAddSteps.setOnClickListener {
            val stepsToAdd = editTextSteps.text.toString().toIntOrNull()
            if (stepsToAdd != null && selectedChallengePosition != -1) {
                val selectedChallenge = challengeList[selectedChallengePosition]
                // Update the steps for the selected challenge
                selectedChallenge.steps += stepsToAdd
                // Save the updated steps locally
                saveStepsLocally(selectedChallenge.name, selectedChallenge.steps)
                // Refresh the UI
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Please select a challenge and enter valid steps.", Toast.LENGTH_SHORT).show()
            }
        }

        // Button click to add steps
        btnAddSteps.setOnClickListener {
            val stepsToAdd = editTextSteps.text.toString().toIntOrNull()
            if (stepsToAdd != null && selectedChallengePosition != -1) {
                val selectedChallenge = challengeList[selectedChallengePosition]
                // Update the steps for the selected challenge
                selectedChallenge.steps += stepsToAdd
                // Save the updated steps locally
                saveStepsLocally(selectedChallenge.name, selectedChallenge.steps)
                // Refresh the UI
                adapter.notifyDataSetChanged()

                // Notify DashboardActivity with the updated challenge
                notifyDashboardActivity(selectedChallenge)
            } else {
                Toast.makeText(this, "Please select a challenge and enter valid steps.", Toast.LENGTH_SHORT).show()
            }
        }

        // Button click to update steps for the selected challenge
        btnUpdateStepsList.setOnClickListener {
            // Handle the button click, e.g., open a dialog or navigate to another activity for updating steps
            // You may want to pass the selected challenge to the next activity or dialog
        }
    }

    private fun getChallengeList(): List<fitnesschallenges> {
        // Retrieve challenge list data (e.g., from SharedPreferences or a local database)
        // For simplicity, I'm using hardcoded values here
        return listOf(
            fitnesschallenges("Walking Challenge 1", getStoredSteps("Walking Challenge 1"), 30),
            fitnesschallenges("Walking Challenge 2", getStoredSteps("Walking Challenge 2"), 20),
            // Add more challenges as needed
        )
    }

    private fun getStoredSteps(challengeName: String): Int {
        // Retrieve stored steps from SharedPreferences
        val sharedPreferences = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(challengeName, 0)
    }

    private fun saveStepsLocally(challengeName: String, steps: Int) {
        // Save steps locally using SharedPreferences
        val sharedPreferences = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(challengeName, steps)
        editor.apply()
    }

    private fun notifyDashboardActivity(updatedChallenge: fitnesschallenges) {
        // Notify DashboardActivity with the updated challenge
        val resultIntent = Intent()
        resultIntent.putExtra("updatedChallenge", updatedChallenge)
        setResult(Activity.RESULT_OK, resultIntent)
    }
}

private fun Intent.putExtra(s: String, updatedChallenge: fitnesschallenges) {

}
