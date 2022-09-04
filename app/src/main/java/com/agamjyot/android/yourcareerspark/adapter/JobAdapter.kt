package com.agamjyot.android.yourcareerspark.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.agamjyot.android.yourcareerspark.databinding.JobLayoutItemBinding
import com.agamjyot.android.yourcareerspark.fragments.MainFragmentDirections
import com.agamjyot.android.yourcareerspark.models.Job

class JobAdapter(private var jobs: ArrayList<Job>) :
    RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    private var binding: JobLayoutItemBinding? = null

    inner class JobViewHolder(itemBinding: JobLayoutItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var item: JobLayoutItemBinding
        fun bind(currentJob: Job) {
            binding?.tvCompanyName?.text = currentJob.companyName
            binding?.tvJobTitle?.text = currentJob.title
            binding?.tvJobType?.text = currentJob.jobType
            binding?.tvJobLocation?.text = currentJob.candidateRequiredLocation

            val date = currentJob.publicationDate?.split("T")
            binding?.tvDate?.text = date?.get(0)

//                Glide.with(this).load(currentJob.companyLogoUrl).into(binding?.ivCompanyLogo!!)
            binding?.root?.setOnClickListener {
                val direction =
                    MainFragmentDirections.actionMainFragmentToJobDetailsFragment(currentJob)
                it.findNavController().navigate(direction)
            }
        }

        init {
            item = itemBinding
        }
    }

//    private object Callback : DiffUtil.ItemCallback<Job>() {
//        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
//            return oldItem == newItem
//        }
//    }

//    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        binding = JobLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job: Job = jobs[position]
        holder.bind(job)
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

}