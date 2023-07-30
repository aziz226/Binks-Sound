package com.exopen.binkssound.AlertClass;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;

import com.exopen.binkssound.R;

public class ShowAlertDialog {
    Context context;
    String message;

    public ShowAlertDialog(Context context) {
        this.context= context;
        this.message= message;

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);
        dialog.setCancelable(false);
        Button dialogButton = (Button) dialog.findViewById(R.id.btn_ok);


        dialogButton.setOnClickListener(v ->
                dialog.dismiss()
        );
        dialog.show();

    }
}
