package com.example.joao.berrantinho.model;

import android.accounts.Account;

/**
 * Created by João Carlos on 2/23/18.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class AccountHolder {
    private final Account account;
    private final String token;

    public AccountHolder(Account account, String token) {
        this.account = account;
        this.token = token;
    }

    public Account getAccount() {
        return account;
    }

    public String getToken() {
        return token;
    }
}
