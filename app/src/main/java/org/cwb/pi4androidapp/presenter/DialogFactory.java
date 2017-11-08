package org.cwb.pi4androidapp.presenter;

import android.app.Activity;
import android.app.AlertDialog;

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.model.NetworkingError;

/**
 * Creates Dialogs for various scenarios
 */
public class DialogFactory {
    private DialogFactory(){} //util class

    public static AlertDialog getDialogForError(Activity activity, NetworkingError error){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage(error.getMessage())
                .setTitle(R.string.dialog_title);

        return builder.create();
    }

    public static AlertDialog getSuccessDialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage("Appointment Made")
                .setTitle("Success!");

        return builder.create();
    }
}
