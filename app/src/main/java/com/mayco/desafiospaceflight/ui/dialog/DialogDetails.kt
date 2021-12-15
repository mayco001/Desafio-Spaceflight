package com.mayco.desafiospaceflight.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.mayco.desafiospaceflight.R
import com.mayco.desafiospaceflight.databinding.FragmentDetailsBinding
import com.mayco.desafiospaceflight.network.response.NewsResponse
import org.koin.android.viewmodel.ext.android.viewModel

class DialogDetails : DialogFragment(), DialogListener {


    lateinit var binding: FragmentDetailsBinding

    private val viewModel: DialogDetailsViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.fullScreemDialog)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniViewModel()


    }

    private fun iniViewModel() {

//        viewModel.getClick()

        viewModel.listener = this


        viewModel.news.observe(requireActivity(), Observer<NewsResponse> {
            binding.textSummary.text = it.summary
            Glide.with(binding.image.context).load(it.imageUrl).into(binding.image)
        })


    }

    override fun onClose() {
        dialog?.dismiss()

    }


}