package dfoode.partnerapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dfoode.partnerapplication.R;
import dfoode.partnerapplication.utils.Utils;

public class OrderListFragment extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_list,null,false);
        initUI();
        return view;
    }

    private void initUI() {
        view.findViewById(R.id.textOrderList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.moveToFragment(getActivity(),new OrderDetailFragment(),null);
            }
        });
    }
}
