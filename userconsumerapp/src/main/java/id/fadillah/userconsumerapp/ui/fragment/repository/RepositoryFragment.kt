package id.fadillah.userconsumerapp.ui.fragment.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.fadillah.userconsumerapp.data.model.UserEntity
import id.fadillah.userconsumerapp.databinding.FragmentRepositoryBinding
import id.fadillah.userconsumerapp.ui.adapter.ListRepositoryAdapter
import id.fadillah.userconsumerapp.ui.fragment.followers.FollowersFragment.ViewState
import id.fadillah.userconsumerapp.viewmodel.ViewModelFactory

class RepositoryFragment : Fragment() {
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!
    private val repositoryAdapter: ListRepositoryAdapter by lazy { ListRepositoryAdapter() }
    private lateinit var viewModel: RepositoryViewModel
    private var userEntity: UserEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRepositoryBinding.inflate(layoutInflater)
        setView(ViewState.LOADING)
        with(binding.rvRepository) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repositoryAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[RepositoryViewModel::class.java]

//        Data disimpan kedalam viewModel agar bertahan saat perubahan theme yg kembali memanggil onViewCreated
        userEntity?.let { viewModel.setUser(it) }
        getData()
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
                binding.rvRepository.visibility = View.VISIBLE
            }
            ViewState.EMPTY -> {
                binding.layoutEmpty.visibility = View.VISIBLE
                binding.layoutShimmer.visibility = View.GONE
                binding.rvRepository.visibility = View.GONE
            }
            ViewState.LOADING -> {
                binding.layoutShimmer.visibility = View.VISIBLE
                binding.layoutEmpty.visibility = View.GONE
                binding.rvRepository.visibility = View.GONE
            }
        }
    }

    private fun getData() {
        if (viewModel.getUser() == null) {
            setView(ViewState.EMPTY)
        } else {
            if (viewModel.getUser()?.repository == 0) {
                setView(ViewState.EMPTY)
            } else {
                viewModel.getUser()?.username?.let { username ->
                    viewModel.getListRepository(username).observe(viewLifecycleOwner, {
                        repositoryAdapter.setData(it)
                        repositoryAdapter.notifyDataSetChanged()
                        setView(ViewState.LOADED)
                    })
                }
            }
        }
    }

    fun setUserData(user: UserEntity) {
        userEntity = user
    }
}