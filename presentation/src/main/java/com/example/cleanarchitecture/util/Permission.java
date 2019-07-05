package com.example.cleanarchitecture.util;

import androidx.fragment.app.Fragment;

import pub.devrel.easypermissions.EasyPermissions;

public class Permission {
    public static void requestPermissions(Fragment fragment, String rationale, int requestCode, String[] permissions) {
        EasyPermissions.requestPermissions(fragment, rationale, requestCode, permissions);
    }
}
