package com.intprog.passionventurev2.ui.dashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intprog.passionventurev2.databinding.FragmentDashboardBinding
import com.intprog.passionventurev2.JobItem
import com.intprog.passionventurev2.JobListAdapter
import com.intprog.passionventurev2.R

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var jobListAdapter: JobListAdapter
    private lateinit var jobList: List<JobItem>

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

        val recyclerView: RecyclerView = binding.recyclerView
        jobListAdapter = JobListAdapter(emptyList(), object : JobListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // Handle item click event
            }
        })
        recyclerView.adapter = jobListAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

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
        updateJobList(jobList)

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateJobList(jobList: List<JobItem>) {
        this.jobList = jobList
        jobListAdapter.filterList(jobList)
    }

    private fun filterJobList(query: String) {
        val filteredList = mutableListOf<JobItem>()
        for (job in jobList) {
            if (job.jobDesc.contains(query, true) || job.jobCompany.contains(query, true)) {
                filteredList.add(job)
            }
        }
        jobListAdapter.filterList(filteredList)
    }
}

