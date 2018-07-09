package dfoode.partnerapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dfoode.partnerapplication.R;
import dfoode.partnerapplication.utils.Utils;

public class NonVegOrdersFragment extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nonveg_orders,null,false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Utils.updateBottomBar(getActivity(), this.getClass().getSimpleName());
        Utils.updateActionBar(getActivity(),this.getClass().getSimpleName(),getString(R.string.non_veg),null,null);
    }
}
