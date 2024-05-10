package com.intprog.passionventurev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView

class JobDetailsView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_details_view)

        val jobTitle = intent.getStringExtra("jobTitle")
        val jobCompany = intent.getStringExtra("jobCompany")
        val jobCategory = intent.getStringExtra("jobCategory")

        val desc = findViewById<TextView>(R.id.descriptionTextView)
        val company = findViewById<TextView>(R.id.companyTextView)
        val category = findViewById<TextView>(R.id.categoryTextView)

        desc.text = jobTitle
        company.text = jobCompany
        category.text = jobCategory

        // Header part
        val backBtn = findViewById<ImageButton>(R.id.backButton)
        val menuBtn = findViewById<ImageButton>(R.id.menuButton)

        backBtn.setOnClickListener {
            onBackPressed() // Use onBackPressed to go back to the previous activity
        }

        menuBtn.setOnClickListener {
            showOverflowMenu(menuBtn)
        }
    }

    private fun showOverflowMenu(anchorView: ImageButton) {
        val popupMenu = PopupMenu(this, anchorView)
        popupMenu.inflate(R.menu.nav_menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_profile -> {
                    // Handle profile action
                    true
                }
                R.id.action_messages -> {
                    // Handle messages action
                    true
                }
                R.id.action_saved_resources -> {
                    // Handle saved resources action
                    true
                }
                R.id.action_logout -> {
                    // Handle logout action
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                // Handle profile action
                true
            }
            R.id.action_messages -> {
                // Handle messages action
                true
            }
            R.id.action_saved_resources -> {
                // Handle saved resources action
                true
            }
            R.id.action_logout -> {
                // Handle logout action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
