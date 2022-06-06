package com.agamjyot.android.yourcareerspark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.agamjyot.android.yourcareerspark.databinding.ActivityMainBinding
import com.agamjyot.android.yourcareerspark.databinding.FragmentMainBinding
import com.agamjyot.android.yourcareerspark.repository.JobRepository
import com.agamjyot.android.yourcareerspark.viewmodel.JobViewModel
import com.agamjyot.android.yourcareerspark.viewmodel.JobViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: JobViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        setUpViewModel()
    }

    private fun setUpViewModel(){
        val jobRepository = JobRepository()
        val viewModelProviderFactory = JobViewModelFactory(
            application,
            jobRepository
        )
        viewModel= ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(JobViewModel :: class.java)
    }
}