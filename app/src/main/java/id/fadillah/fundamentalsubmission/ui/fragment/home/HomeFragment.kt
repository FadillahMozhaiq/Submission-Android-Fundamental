package id.fadillah.fundamentalsubmission.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.fadillah.fundamentalsubmission.databinding.FragmentHomeBinding
import id.fadillah.fundamentalsubmission.ui.adapter.ListUserAdapter
import id.fadillah.fundamentalsubmission.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var userAdapter: ListUserAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = ListUserAdapter()

        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(context)
//            setHasFixedSize(true)
            adapter = userAdapter
        }

        val factory = ViewModelFactory.getInstance(requireContext())
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        userAdapter = ListUserAdapter()

        showRecyclerView(false)
//        viewModel.getAllData().observe(this, {
//            userAdapter.setData(it)
//            userAdapter.notifyDataSetChanged()
//        showRecyclerView(true)
//        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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