package id.fadillah.fundamentalsubmission.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.databinding.ItemUserBinding
import id.fadillah.fundamentalsubmission.ui.activity.detail.DetailActivity
import id.fadillah.fundamentalsubmission.ui.activity.detail.DetailActivity.Companion.EXTRA_DETAIL_DATA
import id.fadillah.fundamentalsubmission.util.ImageHelper

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.UserViewHolder>() {
    private val listUser = ArrayList<UserEntity>()

    fun setData(users: List<UserEntity>?) {
        users ?: return
        listUser.clear()
        listUser.addAll(users)
    }

    class UserViewHolder(private val itemViewBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(user: UserEntity) {
            with(itemViewBinding) {
                tvName.text = user.name
                tvTypeUser.text = user.type
                ImageHelper.getImage(ivProfile, user.image ?: "user1")
                cvItem.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java).apply {
                        putExtra(EXTRA_DETAIL_DATA, user)
                    }
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size
}