package cn.luo.android.quick.ui;

import android.view.KeyEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;

import cn.luo.android.quick.R;
import cn.luo.android.quick.library.annotation.Content;
import cn.luo.android.quick.library.base.BaseWithFragmentActivity;
import cn.luo.android.quick.library.utils.ARouterUtils;

@Content(fragment = MainFragment.class)
@Route(path = ARouterUtils.ACTIVITY_MAIN)
public class MainActivity extends BaseWithFragmentActivity {

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                ToastUtils.showShort("再按一次退出程序");
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initView() {
        super.initView();
        titleView.setShowLeft(false);
        titleView.setTitle(R.string.demo_list);
    }

    @Override
    protected boolean showTitle() {
        return true;
    }
}
