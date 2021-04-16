package id.fadillah.fundamentalsubmission.ui.widget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import id.fadillah.fundamentalsubmission.R
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserUseCase
import id.fadillah.fundamentalsubmission.util.helper.ImageHelper.getBitmapFromURL

internal class StackRemoteViewsFactory(
    private val context: Context,
    private val githubUserUseCase: GithubUserUseCase
) : RemoteViewsService.RemoteViewsFactory {
    private val widgetItem = ArrayList<UserEntity>()

    override fun onCreate() { }

    override fun onDataSetChanged() {
        val data = githubUserUseCase.loadFavoriteForWidget()
        widgetItem.clear()
        for (i in data) {
            widgetItem.add(i)
        }
    }

    override fun onDestroy() { }

    override fun getCount(): Int = widgetItem.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.widget_item)
        rv.setImageViewBitmap(R.id.imageView, getBitmapFromURL(widgetItem[position].image))

        val extras = bundleOf(
            FavoriteUserWidget.EXTRA_ITEM to widgetItem[position].username
        )
        val fillInIntent = Intent()
            .putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = 0

    override fun hasStableIds(): Boolean = false
}