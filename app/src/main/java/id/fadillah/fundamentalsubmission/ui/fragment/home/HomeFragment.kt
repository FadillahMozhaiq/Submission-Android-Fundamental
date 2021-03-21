package id.fadillah.fundamentalsubmission.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.fadillah.fundamentalsubmission.databinding.FragmentHomeBinding
import id.fadillah.fundamentalsubmission.ui.adapter.ListUserAdapter
import id.fadillah.fundamentalsubmission.viewmodel.ViewModelFactory

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val userAdapter: ListUserAdapter by lazy { ListUserAdapter() }
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
        binding.svUser.apply {
            setOnQueryTextListener(this@HomeFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        binding.svUser.clearFocus()
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            if (query.isEmpty())
                getData()
            else
                searchUser(query)
        }
        return true
    }

    private fun searchUser(query: String) {
        showRecyclerView(false)
        homeViewModel.getSearchUsers(query).observe(this, {
            showRecyclerView(true)
            userAdapter.setData(it)
            userAdapter.notifyDataSetChanged()
        })
    }

    private fun getData() {
        showRecyclerView(false)
        homeViewModel.getAllData().observe(viewLifecycleOwner, {
            showRecyclerView(true)
            userAdapter.setData(it)
            userAdapter.notifyDataSetChanged()
        })
    }

    private fun showRecyclerView(show: Boolean) {
        if (show) {
            binding.layoutShimmer.visibility = View.GONE
            binding.rvUser.visibility = View.VISIBLE
        } else {
            binding.layoutShimmer.visibility = View.VISIBLE
            binding.rvUser.visibility = View.GONE
        }
    }
}
