package id.fadillah.fundamentalsubmission.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.fadillah.fundamentalsubmission.R

object ImageHelper {
    private const val TAG = "ImageHelper"
    fun getImage(
        imageView: ImageView,
        url: String,
        placeholder: Int = R.drawable.placeholder_loading,
        error: Int = R.drawable.ic_no_images
    ) {
        Picasso.get()
            .load(getImageId(imageView.context, url))
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

    private fun getImageId(context: Context, imageName: String?): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
}