package com.agamjyot.android.yourcareerspark.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.agamjyot.android.yourcareerspark.databinding.JobLayoutItemBinding
import com.agamjyot.android.yourcareerspark.db.FavJob
import com.agamjyot.android.yourcareerspark.fragments.MainFragmentDirections
import com.agamjyot.android.yourcareerspark.models.Job
import com.bumptech.glide.Glide

class FavJobAdapter constructor(private val itemClick: OnItemClickListener) :
    RecyclerView.Adapter<FavJobAdapter.JobViewHolder>() {

    private var binding: JobLayoutItemBinding? = null

    inner class JobViewHolder(itemBinding: JobLayoutItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val callback = object : DiffUtil.ItemCallback<FavJob>() {
        override fun areItemsTheSame(oldItem: FavJob, newItem: FavJob): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: FavJob, newItem: FavJob): Boolean {
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
            binding?.ibDelete?.visibility = View.VISIBLE

            val date = currentJob.publicationDate?.split("T")
            binding?.tvDate?.text = date?.get(0)

            Glide.with(this).load(currentJob.companyLogoUrl).into(binding?.ivCompanyLogo!!)
        }.setOnClickListener { view ->

            val tags = arrayListOf<String>()
            val job = Job(
                currentJob.candidateRequiredLocation,
                currentJob.category,
                currentJob.companyLogoUrl,
                currentJob.companyName,
                currentJob.description,
                currentJob.jobId,
                currentJob.jobType,
                currentJob.publicationDate,
                currentJob.salary,
                tags,
                currentJob.title,
                currentJob.url
            )
            val direction = MainFragmentDirections.actionMainFragmentToJobDetailsFragment(job)
            view.findNavController().navigate(direction)
        }

        holder.itemView.apply {
            binding?.ibDelete?.setOnClickListener {
                itemClick.onItemClick(currentJob, binding?.ibDelete!!, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnItemClickListener {
        fun onItemClick(
            job: FavJob,
            view: View,
            position: Int
        )
    }


}