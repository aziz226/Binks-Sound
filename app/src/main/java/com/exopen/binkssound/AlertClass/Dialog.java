package com.exopen.binkssound.AlertClass;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.exopen.binkssound.R;

public class Dialog {
    Context context;
    String msg;

    AlertDialog.Builder dialog;
    Class aClass;

    public Dialog(Context context, String msg) {
        this.context = context;
        this.msg = msg;
       // this.aClass= aClass;

         dialog= new AlertDialog.Builder(context);
        dialog.setMessage(msg);
        dialog.setCancelable(false);

        dialog.setPositiveButton(context.getString(R.string.yes),
                (dialog, which) -> {

        });
        dialog.create().show();
    }
}
