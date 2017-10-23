package com.dvrbear.dvrcatdog.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dvrbear.dvrcatdog.MainApplication;
import com.dvrbear.dvrcatdog.controllers.GlobalController;
import com.dvrbear.dvrcatdog.controllers.NavigatorController;

public class BaseFragment extends Fragment{

    public GlobalController global;
    public NavigatorController navigator;
    public View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator = ((MainApplication) getActivity().getApplication()).getNavigatorController();
        global = ((MainApplication) getActivity().getApplication()).getGlobalController();
    }

}
