package com.exopen.binkssound.AlertClass;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;

import com.exopen.binkssound.R;
import com.tapadoo.alerter.Alerter;

public class ErrorAlert {
    Context context;
    String message;

    public ErrorAlert() {}

    public ErrorAlert(Context context, String message) {
        this.context = context;
        this.message = message;

        Alerter.create((Activity) context)
                .setTitle("Erreur:")
                .setIcon(R.drawable.ic_close)
                .setBackgroundColorRes(R.color.red)
                .setDuration(3000)
                .enableSwipeToDismiss()
                .setText(message)
                .setTitleTypeface(Typeface.createFromAsset(context.getAssets(), "bold.ttf"))
                .setTextTypeface(Typeface.createFromAsset(context.getAssets(), "med.ttf"))
                .show();
    }
}
