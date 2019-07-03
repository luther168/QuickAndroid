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

    private static final String GROUP_APP = "/app/";
    private static final String GROUP_APP_ACTIVITY = GROUP_APP + GROUP_ACTIVITY;

    public static final String ACTIVITY_SQLCIPHER_HELPER = GROUP_APP_ACTIVITY + "sqlcipherHelper";
    public static final String ACTIVITY_DATABASE = GROUP_APP_ACTIVITY + "database";
    public static final String ACTIVITY_ARCGIS = GROUP_APP_ACTIVITY + "arcgis";

    public static void toMainActivity() {
        toActivity(ACTIVITY_MAIN);
    }
}
