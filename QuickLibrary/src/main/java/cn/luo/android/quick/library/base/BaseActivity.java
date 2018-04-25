package cn.luo.android.quick.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

/**
 * AUTHOR:      Luo
 * VERSION:     V1.0
 * DESCRIPTION: description
 * CREATE TIME: 2018/4/3 16:41
 * NOTE:
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected AppCompatActivity activity;
    protected Context context;
    protected SparseArray<BaseFragment> fragmentSparseArray = new SparseArray<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        context = this;

        int layoutResId = getLayoutResId();
        if (layoutResId > 0) {
            setContentView(layoutResId);
        }

        initBeforeView();
        initView();
        initAfterView();
    }

    /**
     * 切换Fragment
     *
     * @param toBaseFragment 目标Fragment
     */
    protected void switchFragment(BaseFragment toBaseFragment, int container) {
        BaseFragment fromBaseFragment = fragmentSparseArray.get(container);
        if (fromBaseFragment != toBaseFragment) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            if (fromBaseFragment != null) {
                transaction.hide(fromBaseFragment);
                //Fragment退出
                fromBaseFragment.onFragmentExit();
            }
            if (!toBaseFragment.isAdded()) {
                // 先判断是否被add过
                // 隐藏当前的fragment，add下一个到Activity中
                transaction.add(container, toBaseFragment).commit();
            } else {
                // 隐藏当前的fragment，显示下一个
                transaction.show(toBaseFragment).commit();
            }
            fragmentSparseArray.put(container, toBaseFragment);
            //进入当前的exit
            toBaseFragment.onFragmentEnter();
        }
    }

    protected abstract int getLayoutResId();

    protected abstract void initBeforeView();

    protected abstract void initView();

    protected abstract void initAfterView();

}
