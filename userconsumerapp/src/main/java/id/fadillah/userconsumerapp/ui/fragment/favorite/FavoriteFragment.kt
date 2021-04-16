package id.fadillah.userconsumerapp.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.fadillah.userconsumerapp.databinding.FragmentFavoriteBinding
import id.fadillah.userconsumerapp.ui.adapter.ListUserAdapter
import id.fadillah.userconsumerapp.ui.fragment.followers.FollowersFragment
import id.fadillah.userconsumerapp.viewmodel.ViewModelFactory

class FavoriteFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val userAdapter: ListUserAdapter by lazy { ListUserAdapter() }
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        with(binding.rvUserFavorite) {
            layoutManager =  LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
        binding.svSearchFavorite.apply {
            setOnQueryTextListener(this@FavoriteFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        binding.svSearchFavorite.clearFocus()
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
        setView(FollowersFragment.ViewState.LOADING)
        favoriteViewModel.getSearchListFavoriteUser(query).observe(this, {
            if (it.isEmpty())
                setView(FollowersFragment.ViewState.EMPTY)
            else {
                setView(FollowersFragment.ViewState.LOADED)
                userAdapter.setData(it)
                userAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun getData() {
        setView(FollowersFragment.ViewState.LOADING)
        favoriteViewModel.getListFavoriteUser().observe(viewLifecycleOwner, {
            if (it.isEmpty())
                setView(FollowersFragment.ViewState.EMPTY)
            else {
                setView(FollowersFragment.ViewState.LOADED)
                userAdapter.setData(it)
                userAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setView(state: FollowersFragment.ViewState) {
        when (state) {
            FollowersFragment.ViewState.LOADED -> {
                binding.layoutEmpty.visibility = View.GONE
                binding.layoutShimmer.visibility = View.GONE
                binding.rvUserFavorite.visibility = View.VISIBLE
            }
            FollowersFragment.ViewState.EMPTY -> {
                binding.layoutEmpty.visibility = View.VISIBLE
                binding.layoutShimmer.visibility = View.GONE
                binding.rvUserFavorite.visibility = View.GONE
            }
            FollowersFragment.ViewState.LOADING -> {
                binding.layoutShimmer.visibility = View.VISIBLE
                binding.layoutEmpty.visibility = View.GONE
                binding.rvUserFavorite.visibility = View.GONE
            }
        }
    }
}