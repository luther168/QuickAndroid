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
 * @author      Hurston
 * @version     1.0.0
 * @description
 * @createdTime 2018/11/28 16:16
 * @note
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected BaseActivity activity;
    protected Context context;
    protected LayoutInflater inflater;

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
        this.inflater = inflater;

        initView();
        initAfterView();
        return rootView;
    }

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

    protected abstract void initBeforeView();

    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected abstract void initAfterView();
}
