    package com.intprog.passionventurev2.ui.dashboard

    import android.content.Intent
    import android.os.Bundle
    import android.text.Editable
    import android.text.TextWatcher
    import android.view.LayoutInflater
    import android.view.MenuItem
    import android.view.View
    import android.view.ViewGroup
    import android.widget.EditText
    import android.widget.ImageButton
    import android.widget.TextView
    import androidx.appcompat.widget.PopupMenu
    import androidx.fragment.app.Fragment
    import androidx.lifecycle.ViewModelProvider
    import androidx.navigation.fragment.findNavController
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.intprog.passionventurev2.JobDetailsView
    import com.intprog.passionventurev2.databinding.FragmentDashboardBinding
    import com.intprog.passionventurev2.JobItem
    import com.intprog.passionventurev2.JobListAdapter
    import com.intprog.passionventurev2.R

    class DashboardFragment : Fragment() {

        private var _binding: FragmentDashboardBinding? = null
        private lateinit var jobListAdapter: JobListAdapter
        private lateinit var jobList: List<JobItem>
        private var filteredJobList: List<JobItem> = emptyList() // Add a filtered list to hold the search results

        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

            _binding = FragmentDashboardBinding.inflate(inflater, container, false)
            val root: View = binding.root

            val textView: TextView = binding.textDashboard
            dashboardViewModel.text.observe(viewLifecycleOwner) {
                textView.text = it
            }

            // Sample job items
            jobList = listOf(
                JobItem("50K Sign-on Bonus | Apply Now! CSR-Voice | Start Monday | HMO + Fixed weekends off!", "✅iPloy, OPC Inc.", "Customer Service"),
                JobItem("Senior Software Engineer | Full-time | Remote work available | Competitive salary", "🚀Tech Innovations Ltd.", "Technology"),
                JobItem("Marketing Manager | Part-time | Flexible hours | Bonus opportunities", "🎯Digital Marketing Solutions", "Marketing"),
                JobItem("Customer Support Specialist | Contract | 24/7 shifts | Health benefits", "💼Supportive Solutions Inc.", "Customer Service"),
                JobItem("Data Analyst | Internship | Paid | 3 months | Immediate start", "📊Data Insights Co.", "Data Analysis")
            )

            // Add text changed listener to the search EditText
            val searchEditText: EditText = binding.searchTab
            searchEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // Not needed
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Not needed
                }

                override fun afterTextChanged(s: Editable?) {
                    filterJobList(s.toString())
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

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val recyclerView: RecyclerView = binding.recyclerView
            jobListAdapter = JobListAdapter(filteredJobList, object : JobListAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val jobItem = filteredJobList[position] // Get item from filtered list
                    val intent = Intent(requireContext(), JobDetailsView::class.java).apply {
                        putExtra("jobTitle", jobItem.jobDesc)
                        putExtra("jobCompany", jobItem.jobCompany)
                        putExtra("jobCategory", jobItem.jobCategory)
                    }
                    startActivity(intent)
                }
            })
            recyclerView.adapter = jobListAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            updateJobList(jobList)
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        private fun updateJobList(jobList: List<JobItem>) {
            this.jobList = jobList
            filterJobList("") // Ensure that when the list is updated, it shows all items
        }

        private fun filterJobList(query: String) {
            filteredJobList = if (query.isEmpty()) {
                jobList // If the query is empty, show all items
            } else {
                jobList.filter { job ->
                    job.jobDesc.contains(query, true) || job.jobCompany.contains(query, true)
                }
            }
            jobListAdapter.filterList(filteredJobList)
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

