package id.fadillah.fundamentalsubmission.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.fadillah.fundamentalsubmission.data.model.RepositoryEntity
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.data.source.local.LocalDataSource
import id.fadillah.fundamentalsubmission.data.source.network.RemoteDataSource

class GithubUserRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : UserDataSource {
    companion object {
        private var instance: GithubUserRepository? = null

        fun getInstance(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource) =
            instance ?: synchronized(this) {
                instance ?: GithubUserRepository(localDataSource, remoteDataSource)
            }
    }

    override fun loadAllUser(): LiveData<List<UserEntity>> {
        val list = MutableLiveData<List<UserEntity>>()
        val data = remoteDataSource.getAllUser()
        for (item in data) {

        }
    }

    override fun loadSearchUser(): LiveData<List<UserEntity>> {

    }

    override fun loadDetailUser(): LiveData<UserEntity> {
        TODO("Not yet implemented")
    }

    override fun loadListRepository(username: String): LiveData<List<RepositoryEntity>> {
        TODO("Not yet implemented")
    }

    override fun loadListFollowers(username: String): LiveData<List<UserEntity>> {
        TODO("Not yet implemented")
    }

    override fun loadListFollowing(username: String): LiveData<List<UserEntity>> {
        TODO("Not yet implemented")
    }
}