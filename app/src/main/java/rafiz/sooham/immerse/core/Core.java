package rafiz.sooham.immerse.core;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.orhanobut.logger.Logger;

public class Core {

    private static boolean INITIALIZED = false;

    public static void INIT(Context context, Activity activity){
        if (!INITIALIZED){
            Logger.init("DEBUG").hideThreadInfo();
            CHECK_PERMISSIONS(context, activity);
            INITIALIZED = true;
        }
    }
    public static void CHECK_PERMISSIONS(Context context, Activity activity) {
        int REQUEST_PERMISSION = 1;
        String[] PERMISSIONS = {
                Manifest.permission.CAMERA,
        };
        int camera_permission = ActivityCompat
                .checkSelfPermission(context, Manifest.permission.CAMERA);
        if (camera_permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS,
                    REQUEST_PERMISSION
            );
        }
    }
}
