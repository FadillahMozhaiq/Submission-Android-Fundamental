package id.fadillah.fundamentalsubmission.ui.widget.service

import android.content.Intent
import android.widget.RemoteViewsService
import id.fadillah.fundamentalsubmission.ui.widget.StackRemoteViewsFactory
import id.fadillah.fundamentalsubmission.util.injection.Injection

class StackWidgetService: RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory =
        StackRemoteViewsFactory(applicationContext, Injection.provideGithubUseCase(applicationContext))
}