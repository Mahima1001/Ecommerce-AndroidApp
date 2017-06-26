package com.project.mahima.happyshopping;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {

    public static final String TAG_ALERT = "alertDialog";
    private static final String KEY_ALLOW_ME = "Allow";

    public void MessageToDisplay(String message){
        Bundle bundle= new Bundle();
        bundle.putString(KEY_ALLOW_ME,message);
        setArguments(bundle);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = null;
        Bundle bundle=getArguments();
        String message=bundle.getString(KEY_ALLOW_ME);

        if (getTag().equals(TAG_ALERT)) {

            DialogClick click =
                    new DialogClick();

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Login Failed :(")
                            .setMessage(message)
                            .setIcon(R.drawable.errorpic)
                            .setPositiveButton("OK", click);


            dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
        }
        return dialog;

    }

    private final class DialogClick
            implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            if(which == DialogInterface.BUTTON_POSITIVE){

                // mt(LOGIN AGAIN);
            }
        }
    }


}
