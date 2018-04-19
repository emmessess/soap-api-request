package com.emmessess.soaprequest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Sadiq on 04/16/18.
 */

public class NetworkUtils {
    public static String CONNECTION_ERROR = "Internet Connection Error";
    public static String COMMUNICATION_ERROR = " Communication Error";
    public static boolean isInternetAvailable(Context context){
        // checking internet connectivity
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

}
