package edu.mahnoor.fithabit_tracker_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class activity_record : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        // Assuming you have a list of records, you can update the TextView
        val recordsTextView: TextView = findViewById(R.id.tvRecords)
        val recordsList: List<String> = // Fetch records from your data source

            // Display records in the TextView

    recordsTextView.text = recordsList.joinToString("\n")
    }
}