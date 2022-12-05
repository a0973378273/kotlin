package bean.sample.kotlin

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import bean.sample.kotlin.databinding.FragmentSpaceBinding
import priv.jb.base.basic.BaseFragment

/**
 * below ~ Android 9 - READ_EXTERNAL_STORAGE + WRITE_EXTERNAL_STORAGE
 * Android 10 - READ_EXTERNAL_STORAGE + WRITE_EXTERNAL_STORAGE + android:requestLegacyExternalStorage="true"
 * Android 11 ~ Above - MANAGE_EXTERNAL_STORAGE + obb/data to use SAF
 */
class FileFragment : BaseFragment<FragmentSpaceBinding>() {

    override fun init() {
        checkAndRequestWritePermission {

        }
    }

    override fun initAction() {
//        storageAccessFrameworkRootUri()
    }

    override fun initView() {

    }

    /**
     * Android 5.0 - Android 10 to get external SDCard
     */
    private fun storageAccessFrameworkRootUri() {
        startActivityResult(Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)) {
            val uri = it.data?.data as Uri
            val rootUri = DocumentsContract.buildChildDocumentsUriUsingTree(
                uri,
                DocumentsContract.getTreeDocumentId(uri)
            )
            Log.d("rootUri", "$rootUri")
        }
    }

    @RequiresPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
    @RequiresApi(Build.VERSION_CODES.R)
    private fun isAllFilesAccessPermission(): Boolean = Environment.isExternalStorageManager()

    @RequiresPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
    @RequiresApi(Build.VERSION_CODES.R)
    private fun requestAllFilesAccess(action: (isGrant: Boolean) -> Unit) {
        if (isAllFilesAccessPermission()) {
            action.invoke(true)
        } else {
            startActivityResult(
                Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION),
                rejectAction = {
                    action.invoke(false)
                }) {
                action.invoke(true)
            }
        }
    }

    //    @RequiresPermission(allOf = [Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE])
    private fun isWritePermission(): Boolean {
        return checkPermission(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )
    }

    //    @RequiresPermission(allOf = [Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE])
    private fun checkAndRequestWritePermission(grantAction: () -> Unit) {

//    Log.d(localClassName,"${checkAndRequestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)}")
        checkAndRequestPermission(Manifest.permission.WRITE_CONTACTS) {
//            Log.d(localClassName,"123")
        }
    }

}