package com.example.joao.berrantinho;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.joao.berrantinho.authentication.AccountGeneral;
import com.example.joao.berrantinho.authentication.AuthenticationActivity;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

/**
 * Created by João Carlos on 2/21/18.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class SplashScreen extends AppCompatActivity {
    private static final int REQUEST_GOOGLE_PLAY_SERVICES = 0;
    private static final int REQUEST_AUTHENTICATION = 1;
    private static final int REQUEST_CHECK_ACCOUNT = 2;

    private static final String LOG_TAG = SplashScreen.class.getSimpleName();

    private LoginAsynkTask loginAsynkTask;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_AUTHENTICATION:
                if (resultCode == RESULT_OK) {
                    Intent startupIntent = new Intent(this, MainActivity.class);
                    startupIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(startupIntent);
                }
                break;
            case REQUEST_CHECK_ACCOUNT:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Account[] accounts;
                    if (bundle != null && bundle.containsKey(AccountManager.KEY_ACCOUNTS)) {
                        accounts = (Account[]) bundle.get(AccountManager.KEY_ACCOUNTS);
                        EventBus.getDefault().post(accounts);
                    }
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLoginTask();
    }

    private void checkLoginTask() {

        if (!isPlayServicesAvailable()) {
            Toast.makeText(this, "play service not available", Toast.LENGTH_SHORT).show();
            return;
        } else
            Log.d(LOG_TAG, "play service available");

        String accountType = getResources().getString(R.string.account_type);

        accountType = "com.google";
        Intent intent = AccountPicker.newChooseAccountIntent(null,
                null, new String[]{accountType},
                true, null, null, null, null);
        startActivityForResult(intent, REQUEST_CHECK_ACCOUNT);
    }

    @Subscribe
    public void onAccountReceived(Account[] accounts) {
        if (accounts != null && accounts.length > 0) {
            Intent startupIntent = new Intent(this, MainActivity.class);
            startupIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(startupIntent);
            finish();
        } else {
            Intent startupIntent = new Intent(this, AuthenticationActivity.class);
            startActivityForResult(startupIntent, REQUEST_AUTHENTICATION);
        }
    }

    private static class LoginAsynkTask extends AsyncTask<Void, Void, Account> {

        private WeakReference<Context> contextWeakReference;

        LoginAsynkTask(Context context) {
            this.contextWeakReference = new WeakReference<>(context);
        }

        @Override
        protected Account doInBackground(Void... voids) {

            Context context = contextWeakReference.get();
            if (context == null) return null;

            AccountManager accountManager = AccountManager.get(context);
            Account[] accounts = accountManager.getAccountsByType(AccountGeneral.getAccountType(context));

            //set selected account

            return null;
        }

        @Override
        protected void onPostExecute(Account result) {
            super.onPostExecute(result);
            EventBus.getDefault().post(result);
        }
    }

    private boolean isPlayServicesAvailable() {

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int errorCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        switch (errorCode) {
            case ConnectionResult.SUCCESS:
                return true;
            default:
                return false;
        }
    }
}
