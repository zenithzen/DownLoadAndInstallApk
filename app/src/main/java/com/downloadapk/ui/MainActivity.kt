package com.downloadapk.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.downloadapk.R
import com.downloadapk.util.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_REQUEST_STORAGE = 0
    }

    lateinit var downloadController: DownloadController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (isTaskRoot) {
            val intent = intent
            val intentAction = intent.action
            if (!intent.hasCategory(Intent.CATEGORY_LAUNCHER) &&
                intentAction != null && intentAction == Intent.ACTION_MAIN
            ) {
                finish()
                exitProcess(0)
            }
        }

        // This apk is taking pagination sample app
        // val apkUrl = "https://androidwave.com/source/apk/app-pagination-recyclerview.apk"
        // val apkUrl = "https://dw.uptodown.com/dwn/c4bxHtKPL4UgjhCRvOTj6pp5r7OWfOghc4G39Kc_KosgUBbLFCMlgdtbHb2BsXNcOYsLWbmQZZ-gE972vJCdTGDoWhGc-Z3PySzJev4G4-QTaFR23qOdA2bYuavw-k_J/c52F0YUxiptlOfQRwjIwp7aZKoUaXxQBnkRAZlfKa9LXHbGldBzmHUCxY0pKEfVU7UsDx4z8gp26DZiRYt6dcGje1loImUcZ0pvftHd3trYhgHw1FSa0Ix8eIIKPJWiI/nscxWMlVhkz22DOCJ15Bvkf2haTxQnwaX778Rely-wHIPmvgRoToV_99aD829IZfCw2VkcbTu3G7eKC5Nl2PSuZ9iwlfXmFRSpZQda4PeHUeafcEeToHLVbigObmpofD/"
        val apkUrl = "http://download846.mediafire.com/u778mn6y6dmg/67os70k9p9oyqn5/app-debug.apk"
        downloadController = DownloadController(this, apkUrl)

        buttonDownload.setOnClickListener {
            // check storage permission granted if yes then start downloading file
            checkStoragePermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_STORAGE) {
            // Request for camera permission.
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // start downloading
                downloadController.enqueueDownload()
            } else {
                // Permission request was denied.
                mainLayout.showSnackbar(R.string.storage_permission_denied, Snackbar.LENGTH_SHORT)
            }
        }
    }


    private fun checkStoragePermission() {
        // Check if the storage permission has been granted
        if (checkSelfPermissionCompat(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            // start downloading
            downloadController.enqueueDownload()
        } else {
            // Permission is missing and must be requested.
            requestStoragePermission()
        }
    }

    private fun requestStoragePermission() {

        if (shouldShowRequestPermissionRationaleCompat(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            mainLayout.showSnackbar(
                R.string.storage_access_required,
                Snackbar.LENGTH_INDEFINITE, R.string.ok
            ) {
                requestPermissionsCompat(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_STORAGE
                )
            }

        } else {
            requestPermissionsCompat(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_STORAGE
            )
        }
    }
}
