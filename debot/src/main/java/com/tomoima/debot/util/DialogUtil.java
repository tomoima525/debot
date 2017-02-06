package com.tomoima.debot.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.tomoima.debot.R;

public class DialogUtil {
    public static void showDialog(final Activity activity, String title, String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        if(!StringUtil.isBlankOrNull(title)){
            alertDialogBuilder.setTitle(title);
        }
        if(!StringUtil.isBlankOrNull(message)){
            alertDialogBuilder.setMessage(message);
        }
        alertDialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setCancelable(true);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
