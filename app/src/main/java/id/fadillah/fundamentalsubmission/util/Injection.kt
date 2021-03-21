package id.fadillah.fundamentalsubmission.util

import android.content.Context
import id.fadillah.fundamentalsubmission.data.GithubUserRepository
import id.fadillah.fundamentalsubmission.data.source.local.LocalDataSource
import id.fadillah.fundamentalsubmission.data.source.network.RemoteDataSource
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserInteractor
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserUseCase

object Injection {
    fun provideRepository(): GithubUserRepository {
        val localDataSource = LocalDataSource()
        val remoteDataSource = RemoteDataSource()
        return GithubUserRepository.getInstance(localDataSource, remoteDataSource)
    }

    fun provideGithubUseCase(): GithubUserUseCase {
        val repository = provideRepository()
        return GithubUserInteractor(repository)
    }
}