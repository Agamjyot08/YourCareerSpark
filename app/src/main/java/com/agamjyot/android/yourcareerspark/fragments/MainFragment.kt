package com.agamjyot.android.yourcareerspark.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.agamjyot.android.yourcareerspark.R
import com.agamjyot.android.yourcareerspark.databinding.FragmentMainBinding
import com.agamjyot.android.yourcareerspark.repository.JobRepository
import com.agamjyot.android.yourcareerspark.viewmodel.JobViewModel
import com.agamjyot.android.yourcareerspark.viewmodel.JobViewModelFactory
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    lateinit var viewModel: JobViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabBar()

    }

    private fun setupTabBar() {
        val adapter = FragmentPagerItemAdapter(
            childFragmentManager,
            FragmentPagerItems.with(activity).add("Jobs", JobFragment::class.java)
//                .add("Search", SearchJobFragment::class.java)
                .add("Saved", SavedJobFragment::class.java)
                .create()
        )

        binding.viewpager.adapter = adapter
        binding.viewpagertab.setViewPager(binding.viewpager)
    }
}