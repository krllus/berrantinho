package com.example.joao.berrantinho.authentication;

import android.content.Context;
import android.content.res.Resources;

import com.example.joao.berrantinho.R;

/**
 * Created by João Carlos on 2/21/18.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class AccountGeneral {
    /**
     * Account type id
     */
    public static String getAccountType(Context context) {
        Resources resources = context.getResources();
        return resources.getString(R.string.account_type);
    }

    /**
     * Account name
     */
    public static final String ACCOUNT_NAME = "Udinic";

    /**
     * Auth token types
     */
    public static final String AUTHTOKEN_TYPE_READ_ONLY = "Read only";
    public static final String AUTHTOKEN_TYPE_READ_ONLY_LABEL = "Read only access to an Udinic account";

    public static final String AUTHTOKEN_TYPE_FULL_ACCESS = "Full access";
    public static final String AUTHTOKEN_TYPE_FULL_ACCESS_LABEL = "Full access to an Udinic account";

    //public static final ServerAuthenticate sServerAuthenticate = new ParseComServerAuthenticate();
}
