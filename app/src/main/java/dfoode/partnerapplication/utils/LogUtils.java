package dfoode.partnerapplication.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import dfoode.partnerapplication.R;
import dfoode.partnerapplication.interfaces.OnDialogButtonClicked;


public class LogUtils {



    public static boolean COMING_FROM_ORDER_CREATION = true;

    public static void showToast(Context context, String message) {
        if (context != null) {
            final Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();


        }


    }

    public static void showAlertDialog(Activity activity, String title, String description, final OnDialogButtonClicked dialogButtonClicked) {
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();

        View view = activity.getLayoutInflater().inflate(R.layout.alert_dialog, null);
        alertDialog.setView(view);
        alertDialog.setCanceledOnTouchOutside(false);


        Button btnGoBack = (Button) view.findViewById(R.id.btnGoBack);
        Button btnSubmit = (Button) view.findViewById(R.id.btnSubmit);


        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();
                dialogButtonClicked.goback();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();
                dialogButtonClicked.submit();
            }
        });

        alertDialog.show();
    }


}
