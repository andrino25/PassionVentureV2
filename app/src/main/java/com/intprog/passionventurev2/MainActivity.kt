package com.intprog.passionventurev2

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.intprog.passionventurev2.databinding.ActivityMainBinding
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            // Navigate back to the previous destination
            findNavController(R.id.nav_host_fragment_activity_main).navigateUp()
        }

        val menuButton = findViewById<ImageButton>(R.id.menuButton)
        menuButton.setOnClickListener { view ->
            showOverflowMenu(view)
        }

    }

    private fun showOverflowMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
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

}