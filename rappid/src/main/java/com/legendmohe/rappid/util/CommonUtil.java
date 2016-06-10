package com.legendmohe.rappid.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by legendmohe on 16/3/29.
 */
public class CommonUtil {
    public static <T> boolean isEmpty(T[] t) {
        return !(t != null && t.length != 0);
    }

    public static boolean isEmpty(Collection collection) {
        return !(collection != null && collection.size() != 0);
    }

    public static boolean isEmpty(WeakReference weakReference) {
        return !(weakReference != null && weakReference.get() != null);
    }

    public static boolean validateEmail(String email) {
        if (email.length() > 30) {
            return false;
        }
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean validatePhoneNumber(String number) {
        if (number.length() != 11) {
            return false;
        }

        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static void copyToClipboard(Context context, String content, String label) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(content);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText(label, content);
            clipboard.setPrimaryClip(clip);
        }
    }

    public static String pasteFromClipboard(Context context) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard.getText() != null) {
                return clipboard.getText().toString();
            }
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            if (item.getText() != null) {
                return item.getText().toString();
            }
        }
        return null;
    }

    public static boolean isTheSame(short[] array1, short[] array2) {
        if (array1 == null && array2 == null)
            return true;
        if (array1 == null) {
            array1 = array2;
            array2 = null;
        }
        if (array2 == null)
            return false;
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[2])
                return false;
        }
        return true;
    }

    public static boolean hasPermissionInManifest(Context context, String permissionName) {
        final String packageName = context.getPackageName();
        try {
            final PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            final String[] declaredPermisisons = packageInfo.requestedPermissions;
            if (declaredPermisisons != null && declaredPermisisons.length > 0) {
                for (String p : declaredPermisisons) {
                    if (p.equals(permissionName)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return false;
    }
}
