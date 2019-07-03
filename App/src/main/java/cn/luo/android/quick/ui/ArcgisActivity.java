package cn.luo.android.quick.ui;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.luo.android.quick.R;
import cn.luo.android.quick.library.annotation.Content;
import cn.luo.android.quick.library.base.BaseWithFragmentActivity;
import cn.luo.android.quick.util.RouterUtils;

@Content(fragment = ArcgisFragment.class)
@Route(path = RouterUtils.ACTIVITY_ARCGIS)
public class ArcgisActivity extends BaseWithFragmentActivity {

    @Override
    protected int getFinishType() {
        return 0;
    }

    @Override
    protected boolean showTitle() {
        titleView.setTitle(R.string.arcgis);
        return true;
    }
}
