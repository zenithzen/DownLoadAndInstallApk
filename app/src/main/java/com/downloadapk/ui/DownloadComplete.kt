package com.downloadapk.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.content.FileProvider
import com.downloadapk.BuildConfig
import com.downloadapk.util.DownloadController
import java.io.File

class DownloadComplete(context: Context) :BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val contentUri = FileProvider.getUriForFile(
                context,
                BuildConfig.APPLICATION_ID + DownloadController.PROVIDER_PATH,
                File(destination)
            )
            val install = Intent(Intent.ACTION_VIEW)
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            install.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            install.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true)
            install.data = contentUri
            context.startActivity(install)
            context.unregisterReceiver(this)
            // finish()
        } else {
            val install = Intent(Intent.ACTION_VIEW)
            install.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            install.setDataAndType(
                uri,
                DownloadController.APP_INSTALL_PATH
            )
            context.startActivity(install)
            context.unregisterReceiver(this)
            // finish()
        }*/
    }
}