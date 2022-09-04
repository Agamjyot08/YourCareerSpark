package com.agamjyot.android.yourcareerspark.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.agamjyot.android.yourcareerspark.MainActivity
import com.agamjyot.android.yourcareerspark.R
import com.agamjyot.android.yourcareerspark.adapter.JobAdapter
import com.agamjyot.android.yourcareerspark.databinding.FragmentJobBinding
import com.agamjyot.android.yourcareerspark.network.Resource
import com.agamjyot.android.yourcareerspark.viewmodel.JobViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowWith
import kotlinx.coroutines.launch
import java.util.ArrayList

@AndroidEntryPoint
class JobFragment : Fragment(R.layout.fragment_job) {

    private lateinit var binding: FragmentJobBinding
    private val viewModel: JobViewModel by viewModels()
    private lateinit var jobAdapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJobBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

//        viewModel.jobResult()
        setupObservable()
        Apicall()
    }

    private fun setupRecyclerView() {
        jobAdapter = JobAdapter()

        binding.rvRemoteJobs.apply{
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(object:
                DividerItemDecoration(activity, LinearLayout.VERTICAL){})
            adapter = jobAdapter
        }
        fetchData()
    }

    private fun setupObservable() {
        lifecycleScope.launch {
            viewModel.getDataRes.collectLatest{
                when (it) {
                    is Resource.Success -> {
                        try {
                            list.clear()
                            list.addAll(it.value.card_groups)
                            Log.d("LogTag", list.toString())
                            binding?.contextualCard?.recycler?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                            binding?.contextualCard?.recycler?.adapter = ReAdapter
                        } catch (e: Exception) {
                            Toast.makeText(requireContext(), "oops..! Something went wrong.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    is Resource.Failure ->{
                        this@JobFragment.handleApiError(requireContext(), it)
                    }
                    is Resource.Loading ->{
                        showprogress()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun Apicall() {
        viewModel.getJobs()
    }

//    private fun fetchData() {
//        lifecycleScope.launchWhenCreated {
//            viewModel.infoState.collectLatest {
//
//                when (it) {
//                    is Resource.Success -> {
//                        jobAdapter.submitList(it.data?.jobs)
//                    }
//
//                    is Resource.Error -> {
//                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
//                    }
//                    else -> {}
//                }
//            }
//        }
//    }

}