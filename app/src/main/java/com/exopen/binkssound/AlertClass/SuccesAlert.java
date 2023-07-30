package com.exopen.binkssound.AlertClass;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;

import com.exopen.binkssound.R;
import com.tapadoo.alerter.Alerter;

public class SuccesAlert {

    Context context;
    String message;

    public SuccesAlert() {}

    public SuccesAlert(Context context, String message) {
        this.context = context;
        this.message = message;

        Alerter.create((Activity) context)
                .setTitle("Opération réussie:")
                .setIcon(R.drawable.ic_done)
                .setBackgroundColorRes(R.color.green)
                .setDuration(3000)
                .enableSwipeToDismiss()
                .setText(message)
                .setTitleTypeface(Typeface.createFromAsset(context.getAssets(), "bold.ttf"))
                .setTextTypeface(Typeface.createFromAsset(context.getAssets(), "med.ttf"))
                .show();
    }
}
