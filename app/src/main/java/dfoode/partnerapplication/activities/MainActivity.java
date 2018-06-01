package dfoode.partnerapplication.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dfoode.partnerapplication.R;
import dfoode.partnerapplication.fragments.OrderListFragment;
import dfoode.partnerapplication.utils.LogUtils;
import dfoode.partnerapplication.utils.Utils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.moveToFragment(MainActivity.this,new OrderListFragment(),null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int count = getFragmentManager().getBackStackEntryCount();
        LogUtils.DEBUG("BackStack Count : " +count);

        if (count == 0){
            finish();
        }
        else  if (count > 0){

        }

    }
}
