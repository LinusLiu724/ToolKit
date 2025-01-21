package com.example.toolkit.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

public class RequestPermissionUtil {
    private static final String TAG = "RequestPermissionUtil";
    public static final int REQUEST_CODE_MANAGE_EXTERNAL_STORAGE = 100;
    /**
     * 申请授予所有文件的管理权限
     * @param context
     */
    public static void requestManageStoragePermission(Activity context) {
        Log.d(TAG, "requestManageStoragePermission: ");
        // 检查当前设备是否已授权访问所有文件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                // 启动系统设置页面，用户可以在此界面授权访问所有文件
                Log.d(TAG, "requestManageStoragePermission: startActivityForResult");
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivityForResult(intent, REQUEST_CODE_MANAGE_EXTERNAL_STORAGE);
            } else {
                // 已获得权限，可以继续访问所有文件
                Log.d(TAG, "requestManageStoragePermission: 已获得所有文件的访问权限");
            }
        } else {
            // 对于 Android 11 以下版本，权限检查无效
            Log.d(TAG, "requestManageStoragePermission: 当前版本不支持此权限请求");
        }

    }
}
