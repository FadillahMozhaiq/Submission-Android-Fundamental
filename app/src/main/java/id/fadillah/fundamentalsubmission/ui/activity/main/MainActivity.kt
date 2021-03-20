package id.fadillah.fundamentalsubmission.ui.activity.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.fadillah.fundamentalsubmission.databinding.ActivityMainBinding
import id.fadillah.fundamentalsubmission.ui.adapter.ListUserAdapter
import id.fadillah.fundamentalsubmission.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var userAdapter: ListUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        userAdapter = ListUserAdapter()

        with(binding.content.rvUser) {
            layoutManager = LinearLayoutManager(this@MainActivity)
//            setHasFixedSize(true)
            adapter = userAdapter
        }

        binding.tabs.text

        showRecyclerView(false)
        viewModel.getAllData().observe(this, {
            userAdapter.setData(it)
            userAdapter.notifyDataSetChanged()
            showRecyclerView(true)
        })


    }

    private fun showRecyclerView(show: Boolean) {
        if (show) {
            binding.content.layoutShimmer.visibility = View.GONE
            binding.content.rvUser.visibility = View.VISIBLE
        } else {
            binding.content.layoutShimmer.visibility = View.VISIBLE
            binding.content.rvUser.visibility = View.GONE
        }
    }
}