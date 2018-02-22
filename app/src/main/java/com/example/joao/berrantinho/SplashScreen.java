package com.example.joao.berrantinho;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.joao.berrantinho.authentication.AccountGeneral;
import com.example.joao.berrantinho.authentication.AuthenticatorActivity;
import com.example.joao.berrantinho.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by João Carlos on 2/21/18.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 * based on http://www.zoftino.com/android-account-manager-&-create-custom-account-type
 * other option: https://stackoverflow.com/questions/39804004/google-plus-sign-in-account-selection-dialog-issue
 * other poption: https://stackoverflow.com/questions/31915642/android-login-account-authenticator-vs-manual-authentication
 * account chooser:https://github.com/Udinic/AccountAuthenticator/blob/master/exampleApp/src/main/java/com/udinic/accounts_example/Main1.java
 */

public class SplashScreen extends AppCompatActivity {
    private static final int REQUEST_AUTHENTICATION = 0;
    private static final int REQUEST_ACCOUNT = 1;

    private static final String LOG_TAG = SplashScreen.class.getSimpleName();

    private static final String STATE_DIALOG = "state_dialog";
    private static final String STATE_INVALIDATE = "state_invalidate";
    private AccountManager mAccountManager;
    private AlertDialog mAlertDialog;
    private boolean mInvalidate;

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

            case REQUEST_ACCOUNT:
                if (resultCode == RESULT_OK) {
                    AccountManager accountManager = AccountManager.get(this);

                    String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    String accountType = data.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE);

                    //run though all accounts, get the one with name and selected type
                    Account[] accounts = accountManager.getAccounts();
                    for (Account account : accounts) {
                        if (account.name.equals(accountName) && account.type.equals(accountType)) {
                            EventBus.getDefault().post(account);
                            break;
                        }
                    }
                }
                break;
        }
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountManager = AccountManager.get(this);

        if (savedInstanceState != null) {
            boolean showDialog = savedInstanceState.getBoolean(STATE_DIALOG);
            boolean invalidate = savedInstanceState.getBoolean(STATE_INVALIDATE);
            if (showDialog) {
                showAccountPicker(AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS, invalidate);
            }
        }

        checkUserLoggedIn();
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

    @Subscribe
    public void onAccountReceived(Account account) {
        Log.d(LOG_TAG, "acc received");
        if (account == null) {
            Toast.makeText(this, "acc null", Toast.LENGTH_SHORT).show();
            Intent startupIntent = new Intent(this, AuthenticatorActivity.class);
            startActivityForResult(startupIntent, REQUEST_AUTHENTICATION);
        } else {
            Intent startupIntent = new Intent(this, MainActivity.class);
            startupIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(startupIntent);
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            outState.putBoolean(STATE_DIALOG, true);
            outState.putBoolean(STATE_INVALIDATE, mInvalidate);
        }
    }


    private void checkUserLoggedIn() {
        String accountType = AccountGeneral.getAccountType(this);

        if (!Utils.isPlayServicesAvailable(this)) {
            Toast.makeText(this, "google play services not available in device.", Toast.LENGTH_SHORT).show();
            finish();
        }


        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String accountName = sharedPreferences.getString(getString(R.string.account_name), "");

        final Account availableAccounts[] = mAccountManager.getAccountsByType(AccountGeneral.getAccountType(this));
        for (Account account : availableAccounts) {
            if (account.name.equals(accountName) && account.type.equals(accountType)) {
                getExistingAccountAuthToken(account, accountType);
            }
        }

        showAccountPicker(accountType, false);

//        Intent intent = AccountPicker.newChooseAccountIntent(
//                null,               //selected account
//                null,               // allowable accounts
//                new String[]{accountType},  //allowable account types
//                true,                    //always prompt for account
//                null,                    //description override text
//                null,                   //add acc auth token type
//                null,               //add acc required features
//                null                 //add acc options
//        );
//
//        startActivityForResult(intent, REQUEST_ACCOUNT);
    }

    /**
     * Add new account to the account manager
     *
     * @param accountType
     * @param authTokenType
     */
    private void addNewAccount(String accountType, String authTokenType) {
        final AccountManagerFuture<Bundle> future = mAccountManager.addAccount(
                accountType, authTokenType, null, null,
                this, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        try {
                            Bundle bnd = future.getResult();
                            showMessage("Account was created");
                            Log.d("udinic", "AddNewAccount Bundle is " + bnd);

                        } catch (Exception e) {
                            e.printStackTrace();
                            showMessage(e.getMessage());
                        }
                    }
                }, null);
    }

    /**
     * Show all the accounts registered on the account manager. Request an auth token upon user select.
     */
    private void showAccountPicker(final String authTokenType, final boolean invalidate) {
        mInvalidate = invalidate;
        final Account availableAccounts[] = mAccountManager.getAccountsByType(AccountGeneral.getAccountType(this));

        if (availableAccounts.length == 0) {

        } else {
            String name[] = new String[availableAccounts.length];
            for (int i = 0; i < availableAccounts.length; i++) {
                name[i] = availableAccounts[i].name;
            }

            // Account picker
            mAlertDialog = new AlertDialog.Builder(this)
                    .setTitle("Pick Account")
                    .setAdapter(new ArrayAdapter<>(getBaseContext(),
                                    android.R.layout.simple_list_item_1, name),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (invalidate)
                                        invalidateAuthToken(availableAccounts[which], authTokenType);
                                    else
                                        getExistingAccountAuthToken(availableAccounts[which], authTokenType);
                                }
                            }).create();
            mAlertDialog.show();
        }
    }

    /**
     * Get the auth token for an existing account on the AccountManager
     *
     * @param account
     * @param authTokenType
     */
    private void getExistingAccountAuthToken(Account account, String authTokenType) {
        final AccountManagerFuture<Bundle> future = mAccountManager.getAuthToken(account, authTokenType, null, this, null, null);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bundle bnd = future.getResult();
                    final String authtoken = bnd.getString(AccountManager.KEY_AUTHTOKEN);

                    if (authtoken == null) {

                    }

                    showMessage((authtoken != null) ? "SUCCESS!\ntoken: " + authtoken : "FAIL");
                    Log.d("udinic", "GetToken Bundle is " + bnd);
                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage(e.getMessage());
                }
            }
        }).start();
    }

    /**
     * Invalidates the auth token for the account
     *
     * @param account
     * @param authTokenType
     */
    private void invalidateAuthToken(final Account account, String authTokenType) {
        final AccountManagerFuture<Bundle> future = mAccountManager.getAuthToken(account, authTokenType, null, this, null, null);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bundle bnd = future.getResult();

                    final String authtoken = bnd.getString(AccountManager.KEY_AUTHTOKEN);
                    mAccountManager.invalidateAuthToken(account.type, authtoken);
                    showMessage(account.name + " invalidated");
                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage(e.getMessage());
                }
            }
        }).start();
    }

    /**
     * Get an auth token for the account.
     * If not exist - add it and then return its auth token.
     * If one exist - return its auth token.
     * If more than one exists - show a picker and return the select account's auth token.
     *
     * @param accountType
     * @param authTokenType
     */
    private void getTokenForAccountCreateIfNeeded(String accountType, String authTokenType) {
        final AccountManagerFuture<Bundle> future = mAccountManager.getAuthTokenByFeatures(accountType, authTokenType, null, this, null, null,
                new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        Bundle bnd = null;
                        try {
                            bnd = future.getResult();
                            final String authtoken = bnd.getString(AccountManager.KEY_AUTHTOKEN);
                            showMessage(((authtoken != null) ? "SUCCESS!\ntoken: " + authtoken : "FAIL"));
                            Log.d("udinic", "GetTokenForAccount Bundle is " + bnd);

                        } catch (Exception e) {
                            e.printStackTrace();
                            showMessage(e.getMessage());
                        }
                    }
                }
                , null);
    }

    private void showMessage(final String msg) {
        if (TextUtils.isEmpty(msg))
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
