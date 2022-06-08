package com.agamjyot.android.yourcareerspark.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.agamjyot.android.yourcareerspark.databinding.JobLayoutItemBinding
import com.agamjyot.android.yourcareerspark.fragments.MainFragmentDirections
import com.agamjyot.android.yourcareerspark.models.Job
import com.bumptech.glide.Glide

class JobAdapter : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    private var binding: JobLayoutItemBinding? = null

    inner class JobViewHolder(itemBinding: JobLayoutItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val callback = object : DiffUtil.ItemCallback<Job>() {
        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        binding = JobLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val currentJob = differ.currentList[position]
        Log.d("LOGTAG", "logtag $currentJob")
        holder.itemView.apply {
            binding?.tvCompanyName?.text = currentJob.companyName
            binding?.tvJobTitle?.text = currentJob.title
            binding?.tvJobType?.text = currentJob.jobType
            binding?.tvJobLocation?.text = currentJob.candidateRequiredLocation

            val date = currentJob.publicationDate?.split("T")
            binding?.tvDate?.text = date?.get(0)

            Glide.with(this).load(currentJob.companyLogoUrl).into(binding?.ivCompanyLogo!!)
        }.setOnClickListener {view ->
            val direction = MainFragmentDirections.actionMainFragmentToJobDetailsFragment(currentJob)
            view.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }


}