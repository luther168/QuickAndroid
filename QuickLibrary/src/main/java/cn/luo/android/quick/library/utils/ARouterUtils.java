package cn.luo.android.quick.library.utils;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2018/4/4 9:08
 * NOTE:
 */
public class ARouterUtils {

    public static final String TAG = ARouterUtils.class.getSimpleName();

    public static final String DEFAULT_ROOT_GROUP = "/aRouterUtils/";
    public static final String GROUP_ACTIVITY = "activity/";
    public static final String GROUP_DEFAULT_ACTIVITY = DEFAULT_ROOT_GROUP + GROUP_ACTIVITY;

    public static final String ACTIVITY_SPLASH = GROUP_DEFAULT_ACTIVITY + "splash";
    public static final String ACTIVITY_LOGIN = GROUP_DEFAULT_ACTIVITY + "login";
    public static final String ACTIVITY_MAIN = GROUP_DEFAULT_ACTIVITY + "main";
    public static final String ACTIVITY_ABOUT = GROUP_DEFAULT_ACTIVITY + "about";

    protected static ARouter aRouter = ARouter.getInstance();

    public static void toActivity(String url) {
        aRouter.build(url).navigation();
    }
}
