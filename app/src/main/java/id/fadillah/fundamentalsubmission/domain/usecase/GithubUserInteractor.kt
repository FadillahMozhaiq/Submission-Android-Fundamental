package id.fadillah.fundamentalsubmission.domain.usecase

import androidx.lifecycle.LiveData
import id.fadillah.fundamentalsubmission.data.model.RepositoryEntity
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.domain.repository.IGithubUserRepository

class GithubUserInteractor(private val githubUserRepository: IGithubUserRepository) :
    GithubUserUseCase {
    override fun loadAllUser(): LiveData<List<UserEntity>> = githubUserRepository.loadAllUser()

    override fun loadSearchUser(query: String): LiveData<List<UserEntity>> =
        githubUserRepository.loadSearchUser(query)

    override fun loadDetailUser(username: String): LiveData<UserEntity> =
        githubUserRepository.loadDetailUser(username)

    override fun loadListRepository(username: String): LiveData<List<RepositoryEntity>> =
        githubUserRepository.loadListRepository(username)

    override fun loadListFollowers(username: String): LiveData<List<UserEntity>> =
        githubUserRepository.loadListFollowers(username)

    override fun loadListFollowing(username: String): LiveData<List<UserEntity>> =
        githubUserRepository.loadListFollowing(username)
}