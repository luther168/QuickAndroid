package cn.luo.android.quick.library.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2018/4/3 17:13
 * NOTE:
 */
public class DialogUtils {

    private static AlertDialog dialog = null;

    public static void show(Context context, int titleTextId, int messageTextId,
                            int positiveTextId, DialogInterface.OnClickListener onPositiveClickListener,
                            int negativeTextId, DialogInterface.OnClickListener onNegativeClickListener,
                            boolean cancelable) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(titleTextId)
                .setMessage(messageTextId)
                .setPositiveButton(positiveTextId, onPositiveClickListener)
                .setNegativeButton(negativeTextId, onNegativeClickListener)
                .setCancelable(cancelable);
        dialog = builder.create();
        dialog.show();
    }
}
