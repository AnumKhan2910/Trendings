package com.example.gittrendings.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.gittrendings.databinding.FragmentErrorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentErrorBinding.inflate(inflater, container, false).root
    }
}