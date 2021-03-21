package id.fadillah.fundamentalsubmission.data.source.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.fadillah.fundamentalsubmission.data.source.network.ApiConfig.Companion.getApiService
import id.fadillah.fundamentalsubmission.data.source.network.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        private val TAG = RemoteDataSource::class.java.simpleName

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getAllUser(): LiveData<List<ItemsUserResponse>> {
        val listUser = MutableLiveData<List<ItemsUserResponse>>()

        val client = getApiService().getListUser()
        client.enqueue(object : Callback<List<ItemsUserResponse>> {
            override fun onResponse(
                call: Call<List<ItemsUserResponse>>,
                response: Response<List<ItemsUserResponse>>
            ) {
                if (response.isSuccessful) {
                    listUser.value = response.body()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsUserResponse>>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}", t )
            }
        })

        return listUser
    }

    fun getSearchUser(query: String): LiveData<SearchUserResponse>{
        val listUser = MutableLiveData<SearchUserResponse>()
        val client = getApiService().getSearchUser(query)

        client.enqueue(object : Callback<SearchUserResponse> {
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                if (response.isSuccessful) {
                    listUser.value = response.body()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}", t )
            }
        })

        return listUser
    }

    fun getDetailUser(username: String): LiveData<DetailUserResponse> {
        val detailUser = MutableLiveData<DetailUserResponse>()
        val client = getApiService().getDetailUser(username)

        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful) {
                    detailUser.value = response.body()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}", t )
            }
        })

        return detailUser
    }

    fun getRepositoryUser(username: String): LiveData<List<ItemRepositoryResponse>> {
        val listRepository = MutableLiveData<List<ItemRepositoryResponse>>()
        val client = getApiService().getRepositoryUser(username)

        client.enqueue(object : Callback<List<ItemRepositoryResponse>> {
            override fun onResponse(
                call: Call<List<ItemRepositoryResponse>>,
                response: Response<List<ItemRepositoryResponse>>
            ) {
                if (response.isSuccessful) {
                    listRepository.value = response.body()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemRepositoryResponse>>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}", t )
            }
        })
        return listRepository
    }

    fun getFollowersUser(username: String): LiveData<List<ItemFollowerResponse>> {
        val listFollowers = MutableLiveData<List<ItemFollowerResponse>>()
        val client = getApiService().getFollowersUser(username)

        client.enqueue(object : Callback<List<ItemFollowerResponse>> {
            override fun onResponse(
                call: Call<List<ItemFollowerResponse>>,
                response: Response<List<ItemFollowerResponse>>
            ) {
                if (response.isSuccessful) {
                    listFollowers.value = response.body()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemFollowerResponse>>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}", t )
            }
        })
        return listFollowers
    }

    fun getFollowingUser(username: String): LiveData<List<ItemFollowingResponse>> {
        val listFollowing = MutableLiveData<List<ItemFollowingResponse>>()
        val client = getApiService().getFollowingUser(username)

        client.enqueue(object : Callback<List<ItemFollowingResponse>> {
            override fun onResponse(
                call: Call<List<ItemFollowingResponse>>,
                response: Response<List<ItemFollowingResponse>>
            ) {
                if (response.isSuccessful) {
                    listFollowing.value = response.body()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemFollowingResponse>>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "onFailure: ${t.message}" , t )
            }
        })
        return listFollowing
    }
}