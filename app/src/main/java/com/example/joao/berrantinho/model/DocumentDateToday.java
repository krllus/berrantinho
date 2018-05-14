package com.example.joao.berrantinho.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by João Carlos on 5/14/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class DocumentDateToday implements DocumentDate {
    @Override
    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy", Locale.getDefault());
        Date now = new Date();
        return formatter.format(now);
    }
}
