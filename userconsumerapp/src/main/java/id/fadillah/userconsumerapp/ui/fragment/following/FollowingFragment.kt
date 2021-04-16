package id.fadillah.userconsumerapp.ui.fragment.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.fadillah.userconsumerapp.data.model.UserEntity
import id.fadillah.userconsumerapp.databinding.FragmentFollowingBinding
import id.fadillah.userconsumerapp.ui.adapter.ListUserAdapter
import id.fadillah.userconsumerapp.ui.fragment.followers.FollowersFragment.ViewState
import id.fadillah.userconsumerapp.viewmodel.ViewModelFactory

class FollowingFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val followingAdapter: ListUserAdapter by lazy { ListUserAdapter() }
    private lateinit var viewModel: FollowingViewModel
    private var userEntity: UserEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        setView(ViewState.LOADING)
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = followingAdapter
            setHasFixedSize(true)
        }
        binding.svFollowing.setOnQueryTextListener(this@FollowingFragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[FollowingViewModel::class.java]

//        Data disimpan kedalam viewModel agar bertahan saat perubahan theme yg kembali memanggil onViewCreated
        userEntity?.let { viewModel.setUser(it) }
        getData()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        binding.svFollowing.clearFocus()
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
            }
            ViewState.NOTHING -> {
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
            setView(ViewState.NOTHING)
        } else {
            if (viewModel.getUser()?.following == 0) {
                setView(ViewState.NOTHING)
            } else {
                viewModel.getUser()?.username?.let { username ->
                    viewModel.getListFollowing(username).observe(viewLifecycleOwner, {
                        followingAdapter.setData(it)
                        followingAdapter.notifyDataSetChanged()
                        setView(ViewState.LOADED)
                    })
                }
            }
        }
    }

    private fun searchUser(query: String) {
        if (viewModel.getUser() == null) {
            setView(ViewState.NOTHING)
        } else {
            viewModel.getUser()?.username?.let { username ->
                setView(ViewState.LOADING)
                viewModel.getSearchListFollowing(username, query).observe(viewLifecycleOwner, {
                    followingAdapter.setData(it)
                    followingAdapter.notifyDataSetChanged()
                    if (it.isEmpty())
                        setView(ViewState.EMPTY)
                    else
                        setView(ViewState.LOADED)
                })
            }
        }
    }

    fun setUserFollowing(user: UserEntity) {
        userEntity = user
    }
}