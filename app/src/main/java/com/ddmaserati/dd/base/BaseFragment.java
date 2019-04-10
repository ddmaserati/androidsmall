package com.ddmaserati.dd.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmaserati.dd.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * dec:  基础fragment
 * Created by ddmaserati
 * on 2019/4/10.
 */
public abstract class BaseFragment extends Fragment {
    private Unbinder unbinder;

    public abstract int getLayout();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
