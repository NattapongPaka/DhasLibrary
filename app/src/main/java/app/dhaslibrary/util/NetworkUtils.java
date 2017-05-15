package app.dhaslibrary.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import dhas.dhascheckin.manager.Contextor;

import static android.content.Context.LOCATION_SERVICE;


/**
 * Created by Noth on 23/6/2559.
 */
public class NetworkUtils {

    private Context mContext;
    private static NetworkUtils networkInstance;

    public static NetworkUtils getInstance() {
        if (networkInstance == null) {
            networkInstance = new NetworkUtils();
        }
        return networkInstance;
    }

    private NetworkUtils() {
        this.mContext = Contextor.getInstance().getContext();
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null;
    }

    public boolean isGPSOpen() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        // getting GPS status
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // getting network status
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        return isGPSEnabled && isNetworkEnabled;
    }
}
