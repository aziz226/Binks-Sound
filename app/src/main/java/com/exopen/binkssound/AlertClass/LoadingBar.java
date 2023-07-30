package com.exopen.binkssound.AlertClass;

import android.app.ProgressDialog;
import android.content.Context;

import com.exopen.binkssound.R;

public class LoadingBar {
    Context context;
    String title;
    ProgressDialog dialog;

    public LoadingBar() {
    }

    public LoadingBar(Context context) {
        this.context = context;
        //this.title = title;
        dialog= new ProgressDialog(context);
       // dialog.setTitle(title);
        dialog.setMessage(context.getString(R.string.veuillez_patienter));
        dialog.setCanceledOnTouchOutside(false);
    }
    public void Show(){
        dialog.show();
    }
    public void Dismiss(){
        dialog.dismiss();
    }
}
