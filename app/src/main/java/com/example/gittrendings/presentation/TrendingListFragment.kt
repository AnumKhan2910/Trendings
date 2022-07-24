package com.example.gittrendings.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gittrendings.databinding.FragmentTrendingListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingListFragment: Fragment() {

    private val listViewModel : TrendingListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTrendingListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = listViewModel
            adapter = TrendingDataAdapter {

            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel.fetchTrendingProjects()
    }
}