package cn.luo.android.quick;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2018/4/4 11:49
 * NOTE:
 */
public class App extends Application {

    private static App application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        initLibrary();
    }

    private void initLibrary() {
        Utils.init(application);

        if (isDebug()) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(application);
    }

    private boolean isDebug() {
        return BuildConfig.IS_DEBUG;
    }
}
