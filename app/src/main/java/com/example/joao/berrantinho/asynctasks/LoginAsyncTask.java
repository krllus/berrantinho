package com.example.joao.berrantinho.asynctasks;

import android.accounts.Account;
import android.content.Context;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by João Carlos on 2/22/18.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class LoginAsyncTask extends AsyncTask<Void, Void, Account> {

  private WeakReference<Context> contextWeakReference;

  LoginAsyncTask(Context context) {
    this.contextWeakReference = new WeakReference<>(context);
  }

  @Override
  protected Account doInBackground(Void... voids) {
    Context context = contextWeakReference.get();
    if (context == null) return null;

    //get auth token with server here

    return null;
  }

  @Override
  protected void onPostExecute(Account result) {
    super.onPostExecute(result);
    EventBus.getDefault().post(result);
  }
}
