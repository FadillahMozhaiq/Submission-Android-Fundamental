package id.fadillah.fundamentalsubmission.ui.fragment.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.fadillah.fundamentalsubmission.R
import id.fadillah.fundamentalsubmission.data.model.UserEntity

class FollowingFragment : Fragment() {
    var userEntity: UserEntity? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }
}