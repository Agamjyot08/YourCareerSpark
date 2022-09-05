package com.agamjyot.android.yourcareerspark.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.agamjyot.android.yourcareerspark.R
import com.agamjyot.android.yourcareerspark.adapter.JobAdapter
import com.agamjyot.android.yourcareerspark.databinding.FragmentJobBinding
import com.agamjyot.android.yourcareerspark.models.Job
import com.agamjyot.android.yourcareerspark.utils.Resource
import com.agamjyot.android.yourcareerspark.viewmodel.JobViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JobFragment : Fragment(R.layout.fragment_job) {

    private lateinit var binding: FragmentJobBinding
    private val viewModel: JobViewModel by viewModels()
    private lateinit var jobAdapter: JobAdapter

    private var list: ArrayList<Job> = ArrayList()

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

        jobAdapter = JobAdapter(list)

        setupObservable()
        Apicall()
    }


    private fun setupObservable() {
        lifecycleScope.launch {
            viewModel.getDataRes.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        try {
                            list.clear()
                            list.addAll(it.value.jobs)
                            Log.d("LogTag", list.toString())
                            binding.rvRemoteJobs.apply {
                                layoutManager = LinearLayoutManager(activity)
                                setHasFixedSize(true)
                                addItemDecoration(object :
                                    DividerItemDecoration(activity, LinearLayout.VERTICAL) {})
                                adapter = jobAdapter
                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                requireContext(),
                                "oops..! Something went wrong.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    is Resource.Failure -> {
                        Toast.makeText(
                            requireContext(),
                            "oops..! Something went wrong.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun Apicall() {
        viewModel.getJobs()
    }

}