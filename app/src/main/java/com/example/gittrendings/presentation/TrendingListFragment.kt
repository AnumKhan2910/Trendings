package com.example.gittrendings.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.gittrendings.R
import com.example.gittrendings.databinding.FragmentTrendingListBinding
import com.example.gittrendings.network.TrendingUIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingListFragment: Fragment() {

    private val listViewModel : TrendingListViewModel by viewModels()

    lateinit var binding: FragmentTrendingListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentTrendingListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = listViewModel
            adapter = TrendingDataAdapter {

            }
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.refresh_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                listViewModel.fetchTrendingProjects(true)
            }
            R.id.action_change_mode -> {
                changeMode()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel.fetchTrendingProjects(false)

        listViewModel.viewStateLiveData.observe(this, {
            when(it) {
                is TrendingUIState.Loading -> {
                    showShimmer()
                }
                is TrendingUIState.Failure -> {
                    hideShimmer()
                    navigateToErrorScreen()
                }
                else -> {
                    hideShimmer()
                }
            }
        })
    }

    private fun showShimmer() {
        binding.shimmerViewContainer.visibility = View.VISIBLE
        binding.shimmerViewContainer.startShimmerAnimation()
    }

    private fun hideShimmer() {
        binding.shimmerViewContainer.visibility = View.GONE
        binding.shimmerViewContainer.stopShimmerAnimation()
    }

    private fun navigateToErrorScreen() {
        view?.let {
            Navigation.findNavController(it)
                .navigate(
                    TrendingListFragmentDirections
                        .actionTrendingListFragmentToErrorFragment()
                )
        }
    }

    private fun changeMode() {
        val mode =
            if ((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ==
                Configuration.UI_MODE_NIGHT_NO
            ) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

}