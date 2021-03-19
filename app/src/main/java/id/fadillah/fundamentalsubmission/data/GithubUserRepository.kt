package id.fadillah.fundamentalsubmission.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.data.source.UserDataSource
import id.fadillah.fundamentalsubmission.data.source.local.LocalDataSource

class GithubUserRepository(private val localDataSource: LocalDataSource): UserDataSource {
    companion object {
        private var instance: GithubUserRepository? = null

        fun getInstance(localDataSource: LocalDataSource) =
            instance ?: synchronized(this) {
                instance ?: GithubUserRepository(localDataSource)
            }
    }
    override fun loadAllUser(): LiveData<List<UserEntity>> {
        val list = MutableLiveData<List<UserEntity>>()
        localDataSource.loadAllUser(object : LocalDataSource.LoadAllUserCallback{
            override fun onAllDataUserReceived(data: List<UserEntity>) {
                list.postValue(data)
            }
        })
        return list
    }
}