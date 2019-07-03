package cn.luo.android.quick.ui;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.luo.android.quick.R;
import cn.luo.android.quick.library.annotation.Content;
import cn.luo.android.quick.library.base.BaseWithFragmentActivity;
import cn.luo.android.quick.util.RouterUtils;

@Content(fragment = SqlcipherHelperFragment.class)
@Route(path = RouterUtils.ACTIVITY_SQLCIPHER_HELPER)
public class SqlcipherHelperActivity extends BaseWithFragmentActivity {

    @Override
    protected boolean showTitle() {
        titleView.setTitle(R.string.sqlcipher_helper);
        return true;
    }

    @Override
    protected int getFinishType() {
        return 0;
    }
}
