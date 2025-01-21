package com.example.toolkit;

import static android.app.Activity.RESULT_OK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.example.toolkit.utils.RequestPermissionUtil;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestPermissionUtil.requestManageStoragePermission(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + " resultCode=" + resultCode);
        if (requestCode == RequestPermissionUtil.REQUEST_CODE_MANAGE_EXTERNAL_STORAGE) {
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "onActivityResult: 授予所有文件的管理权限请求被拒绝" );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d(TAG, "onRequestPermissionsResult: " + requestCode);

        if (requestCode == RequestPermissionUtil.REQUEST_CODE_MANAGE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 权限被授予
                Log.d(TAG, "onRequestPermissionsResult: 已授予所有文件的管理权限");
            } else {
                // 权限被拒绝
                Log.d(TAG, "onRequestPermissionsResult: 授予所有文件的管理权限请求被拒绝");
            }
        }
    }
}