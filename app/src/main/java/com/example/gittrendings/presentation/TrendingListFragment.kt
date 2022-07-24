package com.example.gittrendings.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
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
        binding = FragmentTrendingListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = listViewModel
            adapter = TrendingDataAdapter {

            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel.fetchTrendingProjects()

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
}