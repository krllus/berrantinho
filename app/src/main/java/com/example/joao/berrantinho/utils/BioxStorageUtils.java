package com.example.joao.berrantinho.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.model.RelatorioDocument;
import com.example.joao.berrantinho.model.SimpleTextDocument;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

/**
 * Created by João Carlos on 5/11/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class BioxStorageUtils {

    public static boolean createDocument(RelatorioDocument document) {
        if (!isExternalStorageWritable()) {
            Log.d(LOG_TAG, "external storage not writable!");
            return false;
        }

        File relatoriosDir = getDirectoryForDocumentType(document);

        String fileName = getFileNameForDocument(relatoriosDir, document);

        File relatorioTxt = new File(relatoriosDir, fileName);

        try {
            FileWriter writer = new FileWriter(relatorioTxt);
            writer.append(document.getMessage());
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createDocument(SimpleTextDocument document) {
        if (!isExternalStorageWritable()) {
            Log.d(LOG_TAG, "external storage not writable!");
            return false;
        }

        File relatoriosDir = getDirectoryForDocumentType(document);

        String fileName = getFileNameForDocument(relatoriosDir, document);

        File relatorioTxt = new File(relatoriosDir, fileName);

        try {
            FileWriter writer = new FileWriter(relatorioTxt);
            writer.append(document.getMessage());
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static File getDirectoryForDocumentType(BioxDocument document) {
        return getPublicBerranteStorageDir(document.getDocumentSubFolder());
    }

    public static Uri getUriForFile(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getString(R.string.file_provider_authority), file);
    }

    private static File getPublicBerranteStorageDir(String childDirectory) {
        File file = new File(Environment.getExternalStorageDirectory(), "Berrante/" + childDirectory);

        if (!file.exists() && !file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }

    /* Checks if external storage is available for read and write */
    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    private static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    /**
     * based on string format, define the next name to be used as filename for a BioxDocument
     */

    private static String getFileNameForDocument(File rootPath, BioxDocument document) {
        String formatter = document.getFileNameFormatterString();
        File file;
        for (int i = 1; ; i++) {
            file = new File(rootPath, String.format(formatter, i));
            if (!file.exists()) {
                break;
            }
        }

        return file.getName();
    }
}
