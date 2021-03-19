package id.fadillah.fundamentalsubmission.data.source.local

import android.os.Handler
import android.os.Looper
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.util.JsonHelper

class LocalDataSource(private val jsonHelper: JsonHelper) {
    private val handler = Handler(Looper.getMainLooper())

    fun loadAllUser(callback: LoadAllUserCallback) {
        val data = jsonHelper.loadUser()
//        For Showing Loading
        handler.postDelayed({
            callback.onAllDataUserReceived(data)
        }, 3000)
    }

    interface LoadAllUserCallback {
        fun onAllDataUserReceived(data: List<UserEntity>)
    }
}