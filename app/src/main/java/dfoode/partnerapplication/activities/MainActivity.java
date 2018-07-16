package dfoode.partnerapplication.activities;

import android.app.Activity;
import android.os.Bundle;

import dfoode.partnerapplication.R;
import dfoode.partnerapplication.fragments.HomeFragment;
import dfoode.partnerapplication.utils.LogUtils;
import dfoode.partnerapplication.utils.Utils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.moveToFragment(MainActivity.this,new HomeFragment(),null);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();
        LogUtils.DEBUG("BackStack Count : " +count);
         if (count > 0){

        }else {
             super.onBackPressed();
         }

    }
}
