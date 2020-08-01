package cn.luo.android.quick.library.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.SparseArray;

import cn.luo.android.quick.library.R;

/**
 * @author Hurston
 * @version 1.0.0
 * @description
 * @createdTime 2018/11/28 16:16
 * @note
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final int FINISH_DEFAULT = 0;
    public static final int FINISH_BY_SLIDE_OUT_RIGHT = 1;
    public static final int FINISH_BY_SLIDE_OUT_BOTTOM = 2;

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
     * Switch fragment
     *
     * @param toBaseFragment Target fragment
     */
    protected void switchFragment(BaseFragment toBaseFragment, int container) {
        switchFragment(toBaseFragment, container, 0, 0);
    }

    /**
     * Switch fragment
     *
     * @param toBaseFragment Target fragment
     */
    protected void switchFragment(BaseFragment toBaseFragment, int container, int animEnter, int animExit) {
        BaseFragment fromBaseFragment = fragmentSparseArray.get(container);
        if (fromBaseFragment != toBaseFragment) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            if (fromBaseFragment != null) {
                transaction.hide(fromBaseFragment);
                fromBaseFragment.onFragmentExit();
            }
            if (animEnter != 0 && animExit != 0) {
                transaction.setCustomAnimations(animEnter, animExit);
            }
            if (toBaseFragment.isAdded()) {
                transaction.show(toBaseFragment).commit();
            } else {
                transaction.add(container, toBaseFragment).commit();
            }
            fragmentSparseArray.put(container, toBaseFragment);
            toBaseFragment.onFragmentEnter();
        }
    }

    @Override
    public void finish() {
        super.finish();
        switch (getFinishType()) {
            case FINISH_BY_SLIDE_OUT_RIGHT:
                overridePendingTransition(R.anim.no_anim, R.anim.slide_out_right);
                break;
            case FINISH_BY_SLIDE_OUT_BOTTOM:
                overridePendingTransition(R.anim.no_anim, R.anim.slide_out_bottom);
                break;
            case FINISH_DEFAULT:
            default:
                break;
        }
    }

    protected int getFinishType() {
        return FINISH_DEFAULT;
    }

    protected abstract int getLayoutResId();

    protected abstract void initBeforeView();

    protected abstract void initView();

    protected abstract void initAfterView();

}
