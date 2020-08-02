package cn.luo.android.quick;

import cn.luo.android.quick.library.utils.ARouterUtils;

/**
 * @author Luo
 * @version V1.0
 * @description description
 * @createTime 2019/6/29 0:34
 * @note
 */
public class AppRouterUtils extends ARouterUtils {

    public static final String APP_ROOT_GROUP = "/App/";
    public static final String GROUP_APP_ACTIVITY = APP_ROOT_GROUP + GROUP_ACTIVITY;
    public static final String GROUP_APP_FRAGMENT = APP_ROOT_GROUP + GROUP_FRAGMENT;

    public static final String FRAGMENT_ABOUT = GROUP_APP_FRAGMENT + "about";
    public static final String FRAGMENT_DEMO_LIST = GROUP_APP_FRAGMENT + "demoList";
}
