package cn.luo.android.quick.library.utils;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.luo.android.quick.library.base.BaseFragment;
import cn.luo.android.quick.library.base.BaseLoadFailedFragment;

/**
 * @author Hurston
 * @version 1.0.0
 * @description
 * @createdTime 2018/11/28 16:58
 * @note
 */
public class ARouterUtils {

    public static final String TAG = ARouterUtils.class.getSimpleName();

    public static final String DEFAULT_ROOT_GROUP = "/ARouterUtils/";
    public static final String GROUP_ACTIVITY = "activity/";
    public static final String GROUP_FRAGMENT = "fragment/";
    public static final String GROUP_DEFAULT_ACTIVITY = DEFAULT_ROOT_GROUP + GROUP_ACTIVITY;

    public static final String ACTIVITY_SPLASH = GROUP_DEFAULT_ACTIVITY + "splash";
    public static final String ACTIVITY_LOGIN = GROUP_DEFAULT_ACTIVITY + "login";
    public static final String ACTIVITY_MAIN = GROUP_DEFAULT_ACTIVITY + "main";
    public static final String ACTIVITY_ABOUT = GROUP_DEFAULT_ACTIVITY + "about";

    protected static ARouter aRouter = ARouter.getInstance();

    public static void toActivity(String url) {
        aRouter.build(url).navigation();
    }

    public static void toSplashActivity() {
        toActivity(ACTIVITY_SPLASH);
    }

    public static void toLoginActivity() {
        toActivity(ACTIVITY_LOGIN);
    }

    public static void toMainActivity() {
        toActivity(ACTIVITY_MAIN);
    }

    public static void toAboutActivity() {
        toActivity(ACTIVITY_ABOUT);
    }

    public static Fragment getFragment(String url) {
        Object obj = aRouter.build(url).navigation();
        return obj instanceof Fragment ? (Fragment) obj : new BaseLoadFailedFragment();
    }

    public static BaseFragment getBaseFragment(String url) {
        Object obj = aRouter.build(url).navigation();
        return obj instanceof BaseFragment ? (BaseFragment) obj : new BaseLoadFailedFragment();
    }
}
