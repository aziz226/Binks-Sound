package com.exopen.binkssound.AlertClass;

import android.content.Context;
import android.content.Intent;

public class FlagIntent {
    Context context;
    Class aClass;

    public FlagIntent(Context context, Class aClass) {
        this.context = context;
        this.aClass = aClass;

        Intent intent= new Intent(context, aClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
