package id.fadillah.fundamentalsubmission.util

import android.content.Context
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {
    private fun parsingFileToString(filename: String): String? {
        return try {
            val `is` = context.assets.open(filename)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadUser(): List<UserEntity> {
        val list = ArrayList<UserEntity>()
        try {
            val responseObject = JSONObject(parsingFileToString("githubuser.json").toString())
            val listArray = responseObject.getJSONArray("users")
            for (i in 0 until listArray.length()) {
                val user = listArray.getJSONObject(i)

                val username = user.getString("username")
                val name = user.getString("name")
                val avatar = user.getString("avatar")
                val company = user.getString("company")
                val location = user.getString("location")
                val repository = user.getInt("repository")
                val follower = user.getInt("follower")
                val following = user.getInt("following")

                val entity = UserEntity(
                    username,
                    name,
                    avatar,
                    company,
                    location,
                    repository,
                    follower,
                    following
                )
                list.add(entity)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}