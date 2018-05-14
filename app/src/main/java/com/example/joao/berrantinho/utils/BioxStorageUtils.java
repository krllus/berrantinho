package com.example.joao.berrantinho.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.example.joao.berrantinho.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

/**
 * Created by João Carlos on 5/11/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class BioxStorageUtils {
    public static boolean createTxtFile(Context context, String message) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss", Locale.getDefault());
        Date now = new Date();
        String fileName = formatter.format(now) + ".txt";


        if (!isExternalStorageWritable()) {
            Log.d(LOG_TAG, "external storage not writable!");
            return false;
        }

        File relatoriosDir = getRelatoriosDirectory(context);
        File relatorioTxt = new File(relatoriosDir, fileName);

        try {
            FileWriter writer = new FileWriter(relatorioTxt);
            writer.append(message);
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static File getRelatoriosDirectory(Context context) {
        return getPublicBerranteStorageDir(context, "Relatorios/");
    }

    public static File getDatabaseDirectory(Context context) {
        return getPublicBerranteStorageDir(context, "Database/");
    }

    public static Uri getUriForFile(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getString(R.string.file_provider_authority), file);
    }

    private static File getPublicBerranteStorageDir(Context context, String childDirectory) {
        //File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "Berrante/" + childDirectory);
        File file = new File(Environment.getExternalStorageDirectory(), "Berrante/" + childDirectory);

        if (!file.exists() && !file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }
}
