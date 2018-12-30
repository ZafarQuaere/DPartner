package dfoode.partnerapplication.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import dfoode.partnerapplication.R;
import dfoode.partnerapplication.fragments.HomeFragment;
import dfoode.partnerapplication.utils.LogUtils;
import dfoode.partnerapplication.utils.Utils;

public class HomeActivity extends Activity {

    private Activity mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Utils.moveToFragment(HomeActivity.this, new HomeFragment(), null);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStackImmediate();
        int count = getFragmentManager().getBackStackEntryCount();
        LogUtils.DEBUG("BackStack Count : " + count);
        if (count == 0) {
            finish();
        } else {
            if (count > 0) {
                FragmentManager.BackStackEntry entry = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1);//top
                Fragment fragName = (Fragment)getFragmentManager().findFragmentByTag(entry.getName());
                LogUtils.DEBUG("BackStack FragName : " + entry.getName());
                if (entry.getName().equals(new HomeFragment().getClass().getSimpleName())) {
                    Utils.updateActionBar(mContext, this.getClass().getSimpleName(), getString(R.string.home), null, null);
                }
            }
        }
    }
}
