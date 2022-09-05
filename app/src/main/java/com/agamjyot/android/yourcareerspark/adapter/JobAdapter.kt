package com.agamjyot.android.yourcareerspark.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.agamjyot.android.yourcareerspark.databinding.JobLayoutItemBinding
import com.agamjyot.android.yourcareerspark.fragments.MainFragmentDirections
import com.agamjyot.android.yourcareerspark.models.Job
import com.bumptech.glide.Glide

class JobAdapter(private var jobs: ArrayList<Job>) :
    RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    inner class JobViewHolder(var itemBinding: JobLayoutItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        return JobViewHolder(
            JobLayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobs[position]

        holder.itemBinding.tvCompanyName.text = job.companyName
        holder.itemBinding.tvJobTitle.text = job.title
        holder.itemBinding.tvJobType.text = job.jobType
        holder.itemBinding.tvJobLocation.text = job.candidateRequiredLocation
        val date = job.publicationDate?.split("T")
        holder.itemBinding.tvDate.text = date?.get(0)
        Glide.with(holder.itemBinding.root.context).load(job.companyLogoUrl)
            .into(holder.itemBinding.ivCompanyLogo)

        holder.itemBinding.root.setOnClickListener {
            val direction =
                MainFragmentDirections.actionMainFragmentToJobDetailsFragment(job)
            it.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

}