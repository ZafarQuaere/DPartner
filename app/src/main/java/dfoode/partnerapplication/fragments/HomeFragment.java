package dfoode.partnerapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import dfoode.partnerapplication.R;
import dfoode.partnerapplication.utils.Utils;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RelativeLayout rlytOrders;
    private RelativeLayout rlytVeg;
    private RelativeLayout rlytNonVeg;
    private RelativeLayout rlytHistory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);
        initUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Utils.updateBottomBar(getActivity(), this.getClass().getSimpleName());
        Utils.updateActionBar(getActivity(), this.getClass().getSimpleName(), getString(R.string.home), null, null);
    }

    private void initUI() {
        rlytOrders = (RelativeLayout) view.findViewById(R.id.rlytOrders);
        rlytVeg = (RelativeLayout) view.findViewById(R.id.rlytVeg);
        rlytNonVeg = (RelativeLayout) view.findViewById(R.id.rlytNonVeg);
        rlytHistory = (RelativeLayout) view.findViewById(R.id.rlytHistory);

        rlytOrders.setOnClickListener(this);
        rlytVeg.setOnClickListener(this);
        rlytNonVeg.setOnClickListener(this);
        rlytHistory.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlytOrders:
                Utils.moveToFragment(getActivity(), new OrdersFragment(), null);
                break;
            case R.id.rlytVeg:
                Utils.moveToFragment(getActivity(), new VegOrdersFragment(), null);
                break;
            case R.id.rlytNonVeg:
                Utils.moveToFragment(getActivity(), new NonVegOrdersFragment(), null);
                break;
            case R.id.rlytHistory:
                Utils.moveToFragment(getActivity(), new HistoryFragment(), null);
                break;

        }
    }
}
