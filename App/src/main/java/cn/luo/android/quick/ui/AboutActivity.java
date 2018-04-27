package cn.luo.android.quick.ui;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.luo.android.quick.R;
import cn.luo.android.quick.library.annotation.Content;
import cn.luo.android.quick.library.base.BaseWithFragmentActivity;
import cn.luo.android.quick.util.RouterUtils;

@Content(fragment = AboutFragment.class)
@Route(path = RouterUtils.ACTIVITY_ABOUT)
public class AboutActivity extends BaseWithFragmentActivity {

    @Override
    protected void initView() {
        super.initView();

        titleView.setTitle(R.string.about);
    }

    @Override
    protected int getFinishType() {
        return 0;
    }

    @Override
    protected boolean showTitle() {
        return true;
    }
}
