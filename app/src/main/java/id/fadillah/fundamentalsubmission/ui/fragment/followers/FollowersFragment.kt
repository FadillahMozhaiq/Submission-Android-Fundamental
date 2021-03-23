package id.fadillah.fundamentalsubmission.ui.fragment.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.databinding.FragmentFollowersBinding
import id.fadillah.fundamentalsubmission.ui.adapter.ListUserAdapter
import id.fadillah.fundamentalsubmission.viewmodel.ViewModelFactory

class FollowersFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private val followAdapter: ListUserAdapter by lazy { ListUserAdapter() }
    private lateinit var viewModel: FollowersViewModel
    private var userEntity: UserEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        setView(ViewState.LOADING)
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = followAdapter
            setHasFixedSize(true)
        }
        binding.svFollowers.setOnQueryTextListener(this@FollowersFragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[FollowersViewModel::class.java]

        userEntity?.let { viewModel.setUser(it) }
        getData()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        binding.svFollowers.clearFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            if (newText.isEmpty())
                getData()
            else
                searchUser(newText)
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    enum class ViewState {
        LOADED,
        EMPTY,
        LOADING
    }

    private fun setView(state: ViewState) {
        when (state) {
            ViewState.LOADED -> {
                binding.layoutEmpty.visibility = View.GONE
                binding.layoutShimmer.visibility = View.GONE
                binding.rvUser.visibility = View.VISIBLE
                binding.cvSearch.visibility = View.VISIBLE
            }
            ViewState.EMPTY -> {
                binding.layoutEmpty.visibility = View.VISIBLE
                binding.layoutShimmer.visibility = View.GONE
                binding.rvUser.visibility = View.GONE
                binding.cvSearch.visibility = View.GONE
            }
            ViewState.LOADING -> {
                binding.cvSearch.visibility = View.VISIBLE
                binding.layoutShimmer.visibility = View.VISIBLE
                binding.layoutEmpty.visibility = View.GONE
                binding.rvUser.visibility = View.GONE
            }
        }
    }

    private fun getData() {
        if (viewModel.getUser() == null) {
            setView(ViewState.EMPTY)
        } else {
            if (viewModel.getUser()?.followers == 0) {
                setView(ViewState.EMPTY)
            } else {
                viewModel.getUser()?.username?.let { username ->
                    viewModel.getListFollowers(username).observe(viewLifecycleOwner, {
                        followAdapter.setData(it)
                        followAdapter.notifyDataSetChanged()
                        setView(ViewState.LOADED)
                    })
                }
            }
        }
    }

    private fun searchUser(query: String) {
        if (viewModel.getUser() == null) {
            setView(ViewState.EMPTY)
        } else {
            viewModel.getUser()?.username?.let { username ->
                setView(ViewState.LOADING)
                viewModel.getSearchListFollowers(username, query).observe(viewLifecycleOwner, {
                    followAdapter.setData(it)
                    followAdapter.notifyDataSetChanged()
                    if (it.isEmpty())
                        setView(ViewState.EMPTY)
                    else
                        setView(ViewState.LOADED)
                })
            }
        }
    }

    fun setUserFollowers(user: UserEntity) {
        userEntity = user
    }
}