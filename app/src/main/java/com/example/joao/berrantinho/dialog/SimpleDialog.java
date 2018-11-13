package com.example.joao.berrantinho.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.joao.berrantinho.R;

/**
 * Created by Joao Carlos on 11/30/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class SimpleDialog extends DialogFragment {
  public static final String DIALOG_TAG = SimpleDialog.class.getSimpleName();

  @Override
  @NonNull
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

    View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.simple_dialog, null, false);

    final TextView txt = rootView.findViewById(R.id.textView);

    Button cancel = rootView.findViewById(R.id.btn_cancel);
    cancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dismiss();
      }
    });

    Button next = rootView.findViewById(R.id.btn_continue);
    next.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        String message = txt.getText().toString();

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
      }
    });

    builder
        .setTitle(R.string.dialog_title)
        .setView(rootView);

    return builder.create();
  }
}
