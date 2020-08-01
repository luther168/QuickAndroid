package cn.luo.android.quick;

import androidx.databinding.DataBindingUtil;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.AppUtils;

import cn.luo.android.quick.databinding.FragmentAboutBinding;
import cn.luo.android.quick.library.base.BaseFragment;

/**
 * @author Luo
 * @version V1.0
 * @description description
 * @createTime 2019/7/3 0:41
 * @note
 */
@Route(path = AppRouterUtils.FRAGMENT_ABOUT)
public class AboutFragment extends BaseFragment {

    @Override
    protected void initBeforeView() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initView() {
        FragmentAboutBinding binding = DataBindingUtil.bind(rootView);
        if (binding != null) {
            binding.tvVersionName.setText(String.format("For Android V%1$s", AppUtils.getAppVersionName()));
        }
    }

    @Override
    protected void initAfterView() {

    }

    @Override
    public void onClick(View view) {

    }

}
