package id.fadillah.fundamentalsubmission.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.fadillah.fundamentalsubmission.data.model.RepositoryEntity
import id.fadillah.fundamentalsubmission.databinding.ItemRepositoryBinding

class ListRepositoryAdapter: RecyclerView.Adapter<ListRepositoryAdapter.RepositoryViewHolder>() {
    private val listRepository = ArrayList<RepositoryEntity>()

    fun setData(input: List<RepositoryEntity>?) {
        input ?: return
        listRepository.clear()
        listRepository.addAll(input)
    }

    class RepositoryViewHolder(private val itemRepositoryBinding: ItemRepositoryBinding): RecyclerView.ViewHolder(itemRepositoryBinding.root) {

        fun bind(repo: RepositoryEntity) {
            with(itemRepositoryBinding) {
                tvRepositoryDate.text = repo.createAt
                tvRepositoryName.text = repo.name
                if (repo.description.isNullOrEmpty()) {
                    tvDescription.visibility = View.GONE
                } else {
                    tvDescription.text = repo.description
                }
                tvStargazers.text = repo.stargazer.toString()
                if (repo.language == null) {
                    ivLanguage.visibility = View.GONE
                    tvLanguage.visibility = View.GONE
                } else {
                    tvLanguage.text = repo.language
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(listRepository[position])
    }

    override fun getItemCount(): Int = listRepository.size
}