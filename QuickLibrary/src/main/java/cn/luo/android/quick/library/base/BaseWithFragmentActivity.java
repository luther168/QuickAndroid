package cn.luo.android.quick.library.base;

import android.content.Intent;
import android.view.View;

import cn.luo.android.quick.library.R;
import cn.luo.android.quick.library.annotation.Content;
import cn.luo.android.quick.library.view.TitleView;

/**
 * @author      Hurston
 * @version     1.0.0
 * @description
 * @createdTime 2018/11/28 16:17
 * @note
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getCurFragment().onActivityResult(requestCode, resultCode, data);
    }

    protected BaseFragment getCurFragment() {
        return fragmentSparseArray.get(R.id.flFragment);
    }

    protected boolean showTitle() {
        return true;
    }
}
