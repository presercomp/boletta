package com.presercomp.boletta;

import android.content.Context;
import androidx.appcompat.app.AlertDialog;

public class Utilities {

    public static void alert(Context context, String message){
        Utilities.alert(context, message, "");
    }

    public static void alert(Context context, String message, String title){
        String _title = title.length() == 0 ? context.getString(R.string.app_name) : title;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setTitle(_title).setPositiveButton("OK", null).show();
    }

    public static void confirm(Context context, String message, String title,  android.content.DialogInterface.OnClickListener actionYes, android.content.DialogInterface.OnClickListener actionNo ){
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("SI", actionYes)
                .setNegativeButton("NO", actionNo)
                .show();

    }
}
