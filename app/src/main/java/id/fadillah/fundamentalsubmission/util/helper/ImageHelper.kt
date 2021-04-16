package id.fadillah.fundamentalsubmission.util.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.fadillah.fundamentalsubmission.R
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


object ImageHelper {
    private const val TAG = "ImageHelper"
    fun getImage(
        imageView: ImageView,
        url: String,
        placeholder: Int = R.drawable.placeholder_loading,
        error: Int = R.drawable.ic_no_images
    ) {
        Picasso.get()
            .load(url)
            .placeholder(placeholder)
            .error(error)
            .into(imageView, object : Callback {
                override fun onSuccess() {}

                override fun onError(e: Exception?) {
                    Log.e(TAG, "Error: ${e?.message}")
                    Log.e(TAG, "URL: ${e?.message}")
                }
            })
    }

    fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            Log.e("WIDGET", "getBitmapFromURL: ERROR" )
            // Log exception
            null
        }
    }
}