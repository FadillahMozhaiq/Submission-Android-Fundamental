package id.fadillah.fundamentalsubmission.util

import android.content.Context
import id.fadillah.fundamentalsubmission.data.GithubUserRepository
import id.fadillah.fundamentalsubmission.data.source.local.LocalDataSource

object Injection {
    fun provideRepository(context: Context): GithubUserRepository {
        val localDataSource = LocalDataSource(JsonHelper(context))
        return GithubUserRepository.getInstance(localDataSource)
    }
}