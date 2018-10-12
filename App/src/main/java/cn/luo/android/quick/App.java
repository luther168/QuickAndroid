package cn.luo.android.quick;

import android.app.Application;
import android.support.annotation.StringRes;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;

import cn.luo.android.quick.entity.MyObjectBox;
import io.objectbox.BoxStore;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2018/4/4 11:49
 * NOTE:
 */
public class App extends Application {

    private static App application;
    private static BoxStore boxStore;

    public static String getStringValue(@StringRes int resId) {
        return application.getString(resId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        boxStore = MyObjectBox.builder().androidContext(application).build();

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

    public static BoxStore getBoxStore() {
        return boxStore;
    }
}
