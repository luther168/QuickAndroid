package cn.luo.android.quick;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author Luo
 * @version V1.0
 * @description description
 * @createTime 2019/6/29 0:58
 * @note
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

}
