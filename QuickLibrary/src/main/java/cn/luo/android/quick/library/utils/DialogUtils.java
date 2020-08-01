package cn.luo.android.quick.library.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

/**
 * @author Hurston
 * @version 1.0.0
 * @description
 * @createdTime 2018/11/28 17:01
 * @note
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
