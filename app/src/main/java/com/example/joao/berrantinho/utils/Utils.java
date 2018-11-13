package com.example.joao.berrantinho.utils;

import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by João Carlos on 2/22/18.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class Utils {
  public static boolean isPlayServicesAvailable(Context context) {
    GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
    int errorCode = googleApiAvailability.isGooglePlayServicesAvailable(context);

    switch (errorCode) {
      case ConnectionResult.SUCCESS:
        return true;
      default:
        return false;
    }
  }
}
