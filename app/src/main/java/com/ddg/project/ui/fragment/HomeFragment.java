package com.ddg.project.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddg.project.R;
import com.ddg.project.ui.activity.ProductListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, inflate);


        return inflate;
    }


    @OnClick({R.id.makeup_container, R.id.perfumes_container, R.id.skinCare_container, R.id.hair_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.makeup_container:
                getActivity().startActivity(new Intent(getActivity(), ProductListActivity.class).putExtra("category", "Makeup"));
                break;
            case R.id.perfumes_container:
                getActivity().startActivity(new Intent(getActivity(), ProductListActivity.class).putExtra("category", "Perfume"));

                break;
            case R.id.skinCare_container:
                getActivity().startActivity(new Intent(getActivity(), ProductListActivity.class).putExtra("category", "Skin Care"));

                break;
            case R.id.hair_container:
                getActivity().startActivity(new Intent(getActivity(), ProductListActivity.class).putExtra("category", "Hair"));

                break;
        }
    }
}
