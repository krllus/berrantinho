package com.example.joao.berrantinho.authentication;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import com.example.joao.berrantinho.R;

/**
 * Created by João Carlos on 2/21/18.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class AuthenticatorActivity
    extends AccountAuthenticatorActivity
    implements View.OnClickListener {

  public final static String ARG_ACCOUNT_TYPE = "ACCOUNT_TYPE";
  public final static String ARG_AUTH_TYPE = "AUTH_TYPE";
  public final static String ARG_ACCOUNT_NAME = "ACCOUNT_NAME";
  public final static String ARG_IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";

  public static final String KEY_ERROR_MESSAGE = "ERR_MSG";

  public final static String PARAM_USER_PASS = "USER_PASS";
  private static final int MIN_PASSWORD_LENGTH = 4;

  private final int REQ_SIGNUP = 1;

  private AccountManager mAccountManager;
  private String mAuthTokenType;

  private TextInputLayout inputLayoutPassword, inputLayoutEmail;
  private EditText inputPassword;
  private EditText inputEmail;

  private static boolean isValidEmail(String email) {
    return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
        .matches();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // The sign up activity returned that the user has successfully created an account
    if (requestCode == REQ_SIGNUP && resultCode == RESULT_OK) {
      finishLogin(data);
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  @Override
  protected void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.activity_login);
    mAccountManager = AccountManager.get(this);

    inputLayoutEmail = findViewById(R.id.input_email_label);
    inputEmail = findViewById(R.id.input_email);

    inputLayoutPassword = findViewById(R.id.input_password_label);
    inputPassword = findViewById(R.id.input_password);

    String accountName = getIntent().getStringExtra(ARG_ACCOUNT_NAME);
    mAuthTokenType = getIntent().getStringExtra(ARG_AUTH_TYPE);
    if (mAuthTokenType == null) {
      mAuthTokenType = AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS;
    }

    if (accountName != null) {
      inputEmail.setText(accountName);
    }

    findViewById(R.id.submit_login).setOnClickListener(this);
  }

  public void submit() {

    String password = inputPassword.getText().toString().trim();
    String email = inputEmail.getText().toString().trim();

    if (!validateEmail(email)) return;
    if (!validatePassword(password)) return;

    final String accountType = getIntent().getStringExtra(ARG_ACCOUNT_TYPE);

    SubmitLoginTask task = new SubmitLoginTask(new SubmitLoginTask.AsyncResponse() {
      @Override
      public void processFinish(Intent intent) {
        if (intent.hasExtra(KEY_ERROR_MESSAGE)) {
          Toast.makeText(getBaseContext(), intent.getStringExtra(KEY_ERROR_MESSAGE),
              Toast.LENGTH_SHORT).show();
        } else {
          finishLogin(intent);
        }
      }
    });

    task.execute(email, password, accountType);
  }

  private void finishLogin(Intent intent) {
    String accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
    String accountPassword = intent.getStringExtra(PARAM_USER_PASS);
    final Account account =
        new Account(accountName, intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE));
    if (getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false)) {
      String authtoken = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN);
      String authtokenType = mAuthTokenType;

      //todo create bundle with extra fields
      //https://stackoverflow.com/questions/7063280/store-additional-data-in-android-account-manager
      // example below
            /*

            final Bundle extraData = new Bundle();
            bundle.putString("user_email", email);
            mAccountManager.addAccountExplicitly(account, accountPassword, bundle);

            String account_email = account.name;
            String account_password = mAccountManager.getPassword(account);
            String account_username = mAccountManager.getUserData(account, "account_username");
            */

      // Creating the account on the device and setting the auth token we got
      // (Not setting the auth token will cause another call to the server to authenticate the user)
      mAccountManager.addAccountExplicitly(account, accountPassword, null);
      mAccountManager.setAuthToken(account, authtokenType, authtoken);
    } else {
      mAccountManager.setPassword(account, accountPassword);
    }
    setAccountAuthenticatorResult(intent.getExtras());
    setResult(RESULT_OK, intent);
    finish();
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.submit_login:
        submit();
        break;
    }
  }

  private boolean validatePassword(String password) {
    if (TextUtils.isEmpty(password) || !isvalidPassword(password)) {
      inputLayoutPassword.setError(getString(R.string.err_msg_password));
      requestFocus(inputPassword);
      return false;
    } else {
      inputLayoutPassword.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateEmail(String email) {
    if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
      inputLayoutEmail.setError(getString(R.string.err_msg_email));
      requestFocus(inputEmail);
      return false;
    } else {
      inputLayoutEmail.setErrorEnabled(false);
    }

    return true;
  }

  private boolean isvalidPassword(String password) {
    return password.length() > MIN_PASSWORD_LENGTH;
  }

  private void requestFocus(View view) {
    if (view.requestFocus()) {
      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
  }

  private static class SubmitLoginTask extends AsyncTask<String, Void, Intent> {

    private final String TAG = this.getClass().getSimpleName();
    private AsyncResponse listener;

    SubmitLoginTask(AsyncResponse listener) {
      this.listener = listener;
    }

    @Override
    protected Intent doInBackground(String... params) {
      Log.d("udinic", TAG + "> Started authenticating");
      String userName = params[0];
      String userPass = params[1];
      String accountType = params[2];

      String authtoken = null;
      Bundle data = new Bundle();
      try {
        //authtoken = sServerAuthenticate.userSignIn(edtUsername, userPass, mAuthTokenType);
        authtoken = "hahahoho";

        //here, get auth token and other info from server, username for instance;

        data.putString(AccountManager.KEY_ACCOUNT_NAME, userName);
        data.putString(AccountManager.KEY_ACCOUNT_TYPE, accountType);
        data.putString(AccountManager.KEY_AUTHTOKEN, authtoken);
        data.putString(PARAM_USER_PASS, userPass);
      } catch (Exception e) {
        data.putString(KEY_ERROR_MESSAGE, e.getMessage());
      }

      final Intent res = new Intent();
      res.putExtras(data);
      return res;
    }

    @Override
    protected void onPostExecute(Intent intent) {
      listener.processFinish(intent);
    }

    public interface AsyncResponse {
      void processFinish(Intent intent);
    }
  }
}
