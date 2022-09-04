package com.agamjyot.android.yourcareerspark.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.agamjyot.android.yourcareerspark.MainActivity
import com.agamjyot.android.yourcareerspark.R
import com.agamjyot.android.yourcareerspark.databinding.FragmentJobDetailsBinding
import com.agamjyot.android.yourcareerspark.db.FavJob
import com.agamjyot.android.yourcareerspark.models.Job
import com.agamjyot.android.yourcareerspark.viewmodel.JobViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobDetailsFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailsBinding
    private lateinit var currentJob: Job
    private val args: JobDetailsFragmentArgs by navArgs()
    private val viewModel: JobViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJobDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentJob = args.job!!
        setupWebView()

        binding.addFav.setOnClickListener {
            addFavJob(view)
        }
    }

    private fun addFavJob(view: View) {
        val favJob = FavJob(
            0,
            currentJob.candidateRequiredLocation,
            currentJob.category,
            currentJob.companyLogoUrl,
            currentJob.companyName,
            currentJob.description,
            currentJob.id,
            currentJob.jobType,
            currentJob.publicationDate,
            currentJob.salary,
            currentJob.title,
            currentJob.url
        )

        viewModel.addFavJob(favJob)
        Snackbar.make(view, "Job Saved Successfully", Snackbar.LENGTH_LONG).show()
    }

    private fun setupWebView() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            currentJob.url?.let { loadUrl(it) }
        }

        val settings = binding.webView.settings
        settings.apply {
            javaScriptEnabled = true
            setAppCacheEnabled(true)
            cacheMode = WebSettings.LOAD_DEFAULT
            setSupportZoom(false)
            builtInZoomControls = false
            displayZoomControls= false
            textZoom = 100
            blockNetworkImage = false
            loadsImagesAutomatically = true
        }



    }
}