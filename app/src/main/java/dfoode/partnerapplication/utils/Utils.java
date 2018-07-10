package dfoode.partnerapplication.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;

import dfoode.partnerapplication.R;
import dfoode.partnerapplication.fragments.HistoryFragment;
import dfoode.partnerapplication.fragments.HomeFragment;
import dfoode.partnerapplication.fragments.NonVegOrdersFragment;
import dfoode.partnerapplication.fragments.OrdersFragment;
import dfoode.partnerapplication.fragments.VegOrdersFragment;
import dfoode.partnerapplication.interfaces.ActionBarItemClickListener;

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


    public static void updateBottomBar(final Activity activity, final String className) {

        LinearLayout lytBottomBar = (LinearLayout) activity.findViewById(R.id.lytBottomBar);

        LinearLayout lytHomeBar = (LinearLayout) activity.findViewById(R.id.lytHomeBar);
        LinearLayout lytOrders = (LinearLayout) activity.findViewById(R.id.lytOrders);
        LinearLayout lytVegOrder = (LinearLayout) activity.findViewById(R.id.lytVegOrder);
        LinearLayout lytNonvegOrders = (LinearLayout) activity.findViewById(R.id.lytNonvegOrders);
        LinearLayout lytHistoryBar = (LinearLayout) activity.findViewById(R.id.lytHistoryBar);


       /* TextView textBottomHomeIcon = (TextView) lytBottomBar.findViewById(R.id.textBottomHomeIcon);
        TextView textBottomIceOrderIcon = (TextView) lytBottomBar.findViewById(R.id.textBottomIceOrderIcon);
        TextView textBottomHistoryIcon = (TextView) lytBottomBar.findViewById(R.id.textBottomHistoryIcon);
        TextView textBottomEquipRepairIcon = (TextView) lytBottomBar.findViewById(R.id.textBottomEquipRepairIcon);
        TextView textBottomShareFeedIcon = (TextView) lytBottomBar.findViewById(R.id.textBottomShareFeedIcon);*/

        TextView textHomeLable = (TextView) lytBottomBar.findViewById(R.id.textHomeLable);
        TextView textIceOrderLable = (TextView) lytBottomBar.findViewById(R.id.textIceOrderLable);
        TextView textHistoryLable = (TextView) lytBottomBar.findViewById(R.id.textHistoryLable);
        TextView textRepairLable = (TextView) lytBottomBar.findViewById(R.id.textRepairLable);
        TextView textFeedbackLable = (TextView) lytBottomBar.findViewById(R.id.textFeedbackLable);

        lytBottomBar.setVisibility(View.VISIBLE);

        lytHomeBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!AppDialogLoader.getLoader(activity).CheckLoaderStatus()) {
                        if (className.equalsIgnoreCase(new HomeFragment().getClass().getSimpleName())){

                        }else {

                            Utils.moveToFragment(activity, new HomeFragment(), null);
                            updateBottomBar(activity, new HomeFragment().getClass().getSimpleName());
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        lytOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (className.equalsIgnoreCase(new OrdersFragment().getClass().getSimpleName())){

                }else {
                    if (isNetConnected(activity)) {
                        if (!AppDialogLoader.getLoader(activity).CheckLoaderStatus()) {
                            clearBackStackTillHomeFragment(activity);
                            Utils.moveToFragment(activity, new OrdersFragment(), null);
                            updateBottomBar(activity, new OrdersFragment().getClass().getSimpleName());

                        }
                    } else {
                        LogUtils.showToast(activity, activity.getString(R.string.please_check_network_connection));
                    }
                }
            }
        });

        lytHistoryBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (className.equalsIgnoreCase(new HistoryFragment().getClass().getSimpleName())){

                }else {
                    if (Utils.isNetConnected(activity)) {
                        clearBackStackTillHomeFragment(activity);
                        Utils.moveToFragment(activity, new HistoryFragment(), null);
                        updateBottomBar(activity, new HistoryFragment().getClass().getSimpleName());
                    } else {
                        LogUtils.showToast(activity, activity.getString(R.string.please_check_network_connection));
                    }
                }
            }
        });

        lytVegOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!AppDialogLoader.getLoader(activity).CheckLoaderStatus()) {
                    if (className.equalsIgnoreCase(new VegOrdersFragment().getClass().getSimpleName())){

                    }else {
                        clearBackStackTillHomeFragment(activity);
                        Utils.moveToFragment(activity, new VegOrdersFragment(), null);
                    }
                }

            }
        });

        lytNonvegOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (className.equalsIgnoreCase(new NonVegOrdersFragment().getClass().getSimpleName())){

                }else {
                    clearBackStackTillHomeFragment(activity);
                    Utils.moveToFragment(activity, new NonVegOrdersFragment(), null);
                    updateBottomBar(activity, new NonVegOrdersFragment().getClass().getSimpleName());
                }

            }
        });


        if (className.contains(new HomeFragment().getClass().getSimpleName())) {


         /*   textBottomHomeIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorRed)));
            textBottomIceOrderIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomHistoryIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomEquipRepairIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomShareFeedIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));

            textHomeLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorRed)));
            textIceOrderLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textHistoryLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textRepairLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textFeedbackLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));*/
        } else if (className.contains(new OrdersFragment().getClass().getSimpleName())) {
           /* textBottomHomeIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomIceOrderIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorRed)));
            textBottomHistoryIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomEquipRepairIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomShareFeedIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));

            textHomeLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkDarkGrey)));
            textIceOrderLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorRed)));
            textHistoryLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textRepairLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textFeedbackLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));*/
        } else if (className.contains(new VegOrdersFragment().getClass().getSimpleName()) ) {
           /* textBottomHomeIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomIceOrderIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorRed)));
            textBottomHistoryIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomEquipRepairIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomShareFeedIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));

            textHomeLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkDarkGrey)));
            textIceOrderLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorRed)));
            textHistoryLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textRepairLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textFeedbackLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));*/


        } else if (className.contains(new NonVegOrdersFragment().getClass().getSimpleName())) {
           /* textBottomHomeIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomIceOrderIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomHistoryIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomEquipRepairIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorRed)));
            textBottomShareFeedIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));

            textHomeLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textIceOrderLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textHistoryLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textRepairLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorRed)));
            textFeedbackLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));*/

        } else if (className.contains(new HistoryFragment().getClass().getSimpleName())) {
           /* textBottomHomeIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomIceOrderIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomHistoryIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorRed)));
            textBottomEquipRepairIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textBottomShareFeedIcon.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));

            textHomeLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textIceOrderLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textHistoryLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorRed)));
            textRepairLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));
            textFeedbackLable.setTextColor(Color.parseColor(activity.getString(R.color.appColorDarkGrey)));*/
        }
    }


    public static void updateActionBar(final Activity activity, final String className,
                                                    String dynamicTitle, Object customHeaderData,  final ActionBarItemClickListener actionBarItemClickListener) {

        if (activity == null)
            return;

        LogUtils.DEBUG(AppConstant.TAG + " Utils >> updateActionBar() called : " + className + "/" + dynamicTitle);

        RelativeLayout toolbarLayout = (RelativeLayout) activity.findViewById(R.id.toolbar);
        TextView textTitle = (TextView) toolbarLayout.findViewById(R.id.textTitle);
        TextView textBack = (TextView) toolbarLayout.findViewById(R.id.textBack);
        textTitle.setText(dynamicTitle);
        textBack.setVisibility(View.GONE);

        if (className.equals(new HomeFragment().getClass().getSimpleName())) {
            textBack.setVisibility(View.GONE);
            textBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //activity.startActivity(new Intent(activity, LoginActivity.class));

                }
            });
        } else if (className.equals(new OrdersFragment().getClass().getSimpleName())) {
            textBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
        }

        else if (className.equals(new VegOrdersFragment().getClass().getSimpleName())) {
            textBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
        }

        else if (className.equals(new NonVegOrdersFragment().getClass().getSimpleName())) {
            textBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
        }
        else if (className.equals(new HistoryFragment().getClass().getSimpleName())) {
            textBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
        }
    }

    public static void clearBackStackTillHomeFragment(Activity activity) {

        LogUtils.DEBUG("Utils >> clearBackStackTillHomeFragment() >> activity : " + activity);
        if (activity == null) {
            return;
        }
        FragmentManager fm = ((Activity) activity).getFragmentManager();

        for (int i = fm.getBackStackEntryCount() - 1; i >= 0; i--) {
            String fragmentName = (fm.getBackStackEntryAt(i)).getName();
            if (!fragmentName.equals(new HomeFragment().getClass().getName())) {
                fm.popBackStack();
                LogUtils.DEBUG("Utils >> clearBackStackTillHomeFragment() >> removed fragment : " + fragmentName);
            } else {
                break;
            }
        }
       // updateActionBar(activity, new HomeFragment().getClass().getSimpleName(), activity.getString(R.string.reddy_ice), null, null, null);
        updateBottomBar(activity, new HomeFragment().getClass().getSimpleName());
    }

}
