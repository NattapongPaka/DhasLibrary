package app.dhaslibrary.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;


/**
 * Created by patsarapornjaroonpen on 10/12/2016 AD.
 */

public class ScreenUtil {
    private static ScreenUtil instantce;

    public static ScreenUtil getInstantce() {
        if (instantce == null)
            instantce = new ScreenUtil();
        return instantce;
    }

    private Context mContext;

    private ScreenUtil() {
        mContext =  Contextor.getInstance().getContext();
    }

    public int getScreenWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public int getScreenHeight() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public float getScale(){
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return scale;
    }
}
