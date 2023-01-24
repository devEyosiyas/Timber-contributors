package com.eyosiyas.contributors.view

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.eyosiyas.contributors.R
import com.eyosiyas.contributors.adapter.ContributorsAdapter
import com.eyosiyas.contributors.databinding.ActivityMainBinding
import com.eyosiyas.contributors.model.Contributor
import com.eyosiyas.contributors.util.Result
import com.eyosiyas.contributors.viewmodel.ContributorsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.floor


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    /* A lateinit variable that is used to bind the layout to the activity. */
    private lateinit var binding: ActivityMainBinding

    /* This is a lazy initialization of the viewModel. */
    private val viewModel: ContributorsViewModel by lazy {
        ViewModelProvider(this)[ContributorsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* This is a way to bind the layout to the activity. */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Observing the live data that is returned from the viewModel. */
        viewModel.contributors.observe(this) { result ->
            binding.progressBar.visibility = View.VISIBLE
            if (result is Result.Success<*>) {
                val contributors = result.data
                binding.rvContributors.adapter =
                    ContributorsAdapter(contributors as List<Contributor>)

            } else {
                result as Result.Error
                with(binding) {
                    rvContributors.visibility = View.GONE
                    txtError.visibility = View.VISIBLE
                    txtError.text = result.exception.message
                }
            }
            binding.progressBar.visibility = View.GONE
        }
        calculateSpanCount()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE || newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            calculateSpanCount()
        }
    }

    private fun calculateSpanCount() {
        /* This is a way to calculate the span count of the recycler view. */
        binding.rvContributors.viewTreeObserver.addOnGlobalLayoutListener(object :
            OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.rvContributors.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val viewWidth: Int = binding.rvContributors.measuredWidth
                val itemWidth =
                    resources.getDimension(R.dimen.contributor_item_width)
                val spanCount = floor((viewWidth / itemWidth).toDouble()).toInt()
                binding.rvContributors.layoutManager = GridLayoutManager(
                    this@MainActivity, if (spanCount == 0) 1 else spanCount
                )
            }
        })
    }
}