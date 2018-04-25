package cn.luo.android.quick.util;

import cn.luo.android.quick.library.utils.ARouterUtils;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2018/4/4 11:18
 * NOTE:
 */
public class RouterUtils extends ARouterUtils {
    public static final String GROUP_APP = "/app/";
    public static final String GROUP_APP_ACTIVITY = "/app/" + GROUP_ACTIVITY;

    public static void toMainActivity() {
        toActivity(ACTIVITY_MAIN);
    }
}
