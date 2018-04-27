package cn.luo.android.quick.library.base;

import android.view.View;

import com.blankj.utilcode.util.LogUtils;

import cn.luo.android.quick.library.R;
import cn.luo.android.quick.library.annotation.Content;
import cn.luo.android.quick.library.view.TitleView;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2018/4/3 17:39
 * NOTE:
 */
public abstract class BaseWithFragmentActivity extends BaseActivity {

    protected TitleView titleView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base_with_fragment;
    }

    @Override
    protected void initBeforeView() {
        Class<?> clazz = this.getClass();
        if (clazz.isAnnotationPresent(Content.class)) {
            Content content = clazz.getAnnotation(Content.class);
            if (content != null) {
                Class contentClazz = content.fragment();

                try {
                    BaseFragment baseFragment = (BaseFragment) contentClazz.newInstance();
                    baseFragment.setArguments(this.getIntent().getExtras());
                    switchFragment(baseFragment, R.id.flFragment);
                } catch (Exception e) {
                    LogUtils.i("TAG", "异常:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void initView() {
        titleView = findViewById(R.id.titleView);
        titleView.setDefaultClickTitleViewListener(this);
        titleView.setVisibility(showTitle() ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void initAfterView() {

    }

    protected BaseFragment getCurFragment() {
        return fragmentSparseArray.get(R.id.flFragment);
    }

    protected abstract boolean showTitle();
}
