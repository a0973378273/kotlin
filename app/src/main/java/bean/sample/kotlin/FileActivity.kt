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
import androidx.core.content.PermissionChecker
import bean.sample.kotlin.databinding.ActivitySpaceBinding
import bean.sample.util.versionAboveR
import priv.jb.base.basic.BaseActivity

/**
 * ~ Android 9 - READ_EXTERNAL_STORAGE + WRITE_EXTERNAL_STORAGE
 * Android 10 - READ_EXTERNAL_STORAGE + WRITE_EXTERNAL_STORAGE + android:requestLegacyExternalStorage="true"
 * Android 11 ~ Above - MANAGE_EXTERNAL_STORAGE + obb/data to use SAF
 * @return Granted file permission
 */
class FileActivity : BaseActivity<ActivitySpaceBinding>() {

    override fun initAction() {
        storageAccessFrameworkRootUri()
    }

    override fun initView() {
        if (versionAboveR) {
            var isExternalStoragePermission = Environment.isExternalStorageManager()
            Log.d("SDCardPermission", "$isExternalStoragePermission")
        }
        var isReadExternalStoragePermission = PermissionChecker.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PermissionChecker.PERMISSION_GRANTED
        Log.d("ReadFilePermission", "$isReadExternalStoragePermission")
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


    @RequiresApi(Build.VERSION_CODES.R)
    private fun isAllFilesAccessPermission(): Boolean = Environment.isExternalStorageManager()

    @RequiresApi(Build.VERSION_CODES.R)
    private fun requestAllFilesAccess(action: (isGrant: Boolean) -> Unit){
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

}