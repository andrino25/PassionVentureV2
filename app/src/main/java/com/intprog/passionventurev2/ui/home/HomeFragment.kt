package com.intprog.passionventurev2.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intprog.passionventurev2.Mentor
import com.intprog.passionventurev2.MentorAdapter
import com.intprog.passionventurev2.R
import com.intprog.passionventurev2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Sample data creation
        val mentorList = listOf(
            Mentor(R.drawable.profile, "Earl Andrino", "Technology"),
            Mentor(R.drawable.profile3, "Joseph Villariasa", "Business"),
            Mentor(R.drawable.profile2, "Kevin Apiag", "Healthcare"),
            Mentor(R.drawable.random1, "Hirai Momo", "Technology"),
            Mentor(R.drawable.random2, "Sharon Mina", "Business"),
            Mentor(R.drawable.random3, "Alex Johanson", "Healthcare"),
            Mentor(R.drawable.profile, "Earl Andrino", "Technology"),
            Mentor(R.drawable.profile3, "Joseph Villariasa", "Business"),
            Mentor(R.drawable.profile2, "Kevin Apiag", "Healthcare"),
            Mentor(R.drawable.random1, "Hirai Momo", "Technology"),
            Mentor(R.drawable.random2, "Sharon Mina", "Business"),
            Mentor(R.drawable.random3, "Alex Johanson", "Healthcare"),
            // Add more Mentor objects as needed
        )

        // RecyclerView setup
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerView)
        val searchTab: EditText = root.findViewById(R.id.searchTab)
        val adapter = MentorAdapter(requireContext(), mentorList)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        searchTab.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // Not needed
            }
        })

        val toolbar = binding.toolbar
        val backButton = toolbar.findViewById<ImageButton>(R.id.backButton)
        val menuButton = toolbar.findViewById<ImageButton>(R.id.menuButton)

        backButton.setOnClickListener {
            // Navigate back to the previous destination
            findNavController().navigateUp()
        }

        menuButton.setOnClickListener { view ->
            showOverflowMenu(view)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showOverflowMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
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
