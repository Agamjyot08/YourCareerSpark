package com.agamjyot.android.yourcareerspark.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.agamjyot.android.yourcareerspark.MainActivity
import com.agamjyot.android.yourcareerspark.R
import com.agamjyot.android.yourcareerspark.adapter.JobAdapter
import com.agamjyot.android.yourcareerspark.databinding.FragmentJobBinding
import com.agamjyot.android.yourcareerspark.viewmodel.JobViewModel

class JobFragment : Fragment() {

    private lateinit var binding: FragmentJobBinding
    lateinit var viewModel: JobViewModel
    private lateinit var adapter: JobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = JobAdapter()

        binding.rvRemoteJobs.apply{
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(object:
                DividerItemDecoration(activity, LinearLayout.HORIZONTAL){})
            adapter = adapter
        }
        fetchData()
    }

    private fun fetchData() {
        viewModel.jobResult().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.differ.submitList(it.jobs)
            }
        }
    }

}