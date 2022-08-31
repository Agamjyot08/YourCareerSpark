package com.agamjyot.android.yourcareerspark.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.agamjyot.android.yourcareerspark.MainActivity
import com.agamjyot.android.yourcareerspark.adapter.FavJobAdapter
import com.agamjyot.android.yourcareerspark.databinding.FragmentSavedJobBinding
import com.agamjyot.android.yourcareerspark.db.FavJob
import com.agamjyot.android.yourcareerspark.viewmodel.JobViewModel

class SavedJobFragment : Fragment(), FavJobAdapter.OnItemClickListener {
    private lateinit var binding: FragmentSavedJobBinding
    private lateinit var viewModel: JobViewModel
    private lateinit var favAdapter: FavJobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        favAdapter = FavJobAdapter(this)

        binding.rvJobsSaved.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(object: DividerItemDecoration(
                activity, LinearLayout.VERTICAL
            ) {})
            adapter = favAdapter
        }

        viewModel.getAllFavJobs().observe(viewLifecycleOwner) {
            favAdapter.differ.submitList(it)
            updateUI(it)
        }
    }

    private fun updateUI(job: List<FavJob>) {
        if (job.isNotEmpty()) {
            binding.rvJobsSaved.visibility = View.VISIBLE
            binding.cardNoAvailable.visibility = View.GONE
        } else {
            binding.rvJobsSaved.visibility = View.GONE
            binding.cardNoAvailable.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(job: FavJob, view: View, position: Int) {
        deleteJob(job)
    }

    private fun deleteJob(job: FavJob) {
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Job")
            setMessage("Are you sure?")
            setPositiveButton("Delete") {_,_->
                viewModel.deleteFavJob(job)
                Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }
}