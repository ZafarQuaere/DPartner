package dfoode.partnerapplication.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.Serializable;

import dfoode.partnerapplication.R;

public class Utils {

    public static boolean isNetConnected(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());

    }


    public static void hideKeyboard(Context context, View view) {
        if (context == null || view == null) {
            return;
        }
        try {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            LogUtils.ERROR("Error in hideKeyboard() : " + e.toString());
        }

    }

    //navigating to fragment
    public static void moveToFragment(Context context, Fragment fragment, Object data) {
        LogUtils.DEBUG("moveToFragment() called : " + fragment.getClass().getSimpleName());
        if (context == null || fragment == null)
            return;

        FragmentManager manager = ((Activity) context).getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.lytMain, fragment, fragment.getClass().getSimpleName());

        //if data is also transferring
        Bundle bundle = new Bundle();
        if (data != null) {
            bundle.putSerializable(context.getString(R.string.key_serializable), (Serializable) data);
        }
        fragment.setArguments(bundle);

        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();
    }

    public static boolean isValidMobileNumber(String mobileNo) {
        return Patterns.PHONE.matcher(mobileNo)
                .matches();
    }

    public static boolean isValidEmail(Activity activity, String email) {
        if (activity == null)
            return false;

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();

    }

}
