package cn.luo.android.quick.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2018/4/3 17:43
 * NOTE:
 */
public abstract class BaseFragment extends Fragment {

    protected BaseActivity activity;
    protected Context context;

    protected View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
        if (getActivity() instanceof BaseActivity) {
            this.activity = (BaseActivity) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initBeforeView();

        int layoutResId = getLayoutResId();
        rootView = inflater.inflate(layoutResId, container, false);

        initView();
        initAfterView();
        return rootView;
    }

    protected abstract void initBeforeView();

    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected abstract void initAfterView();

    @Override
    public void onPause() {
        super.onPause();
        onFragmentExit();
    }

    @Override
    public void onResume() {
        super.onResume();
        onFragmentEnter();
    }

    public void onFragmentEnter() {
    }

    public void onFragmentExit() {
    }
}
