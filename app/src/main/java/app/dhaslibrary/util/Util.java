package app.dhaslibrary.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import dhas.dhascheckin.manager.Contextor;


/**
 * Created by Dev on 30/1/2560.
 */

public class Util {
    private static Util instance;

    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public boolean isDeviceVersionN() {
        /**
         * Android N = API 24
         */
        if (Build.VERSION.SDK_INT >= 24) {
            return true;
        } else {
            return false;
        }
    }

    public String getAppVersion() {
        PackageManager manager = Contextor.getInstance().getContext().getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(Contextor.getInstance().getContext().getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return "";
    }

    public String getString(int id){
        return Contextor.getInstance().getContext().getString(id);
    }
}
