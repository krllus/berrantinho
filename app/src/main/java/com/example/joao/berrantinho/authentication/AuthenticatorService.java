package com.example.joao.berrantinho.authentication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by João Carlos on 2/21/18.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class AuthenticatorService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        Authenticator authenticator = new Authenticator(this);
        return authenticator.getIBinder();
    }
}
