package com.mayco.desafiospaceflight.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.mayco.desafiospaceflight.R
import com.mayco.desafiospaceflight.databinding.ActivityHomeBinding
import com.mayco.desafiospaceflight.ui.fragment.NewsFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), HomeListener {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initViewModel()

    }

    private fun initViewModel() {
        binding.viewmodel = viewModel
        viewModel.listener = this
        viewModel.getNews()
    }

    override fun apiSuccess() {
        val frameLayout = NewsFragment()
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.containerID, frameLayout)
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

        transition.commit()

        binding.progressBar.visibility = View.INVISIBLE
        binding.loading.visibility = View.INVISIBLE
        binding.spaceFlight.visibility = View.INVISIBLE

    }

    override fun apiError(message: String) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

        binding.progressBar.setVisibility(View.VISIBLE)
    }
}