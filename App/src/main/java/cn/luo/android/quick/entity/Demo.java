package cn.luo.android.quick.entity;

import android.support.annotation.StringRes;

import cn.luo.android.quick.App;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2018/4/4 11:08
 * NOTE:
 */
public class Demo {

    private String title;
    private String routerUrl;

    public Demo(@StringRes int resId, String routerUrl) {
        this.title = App.getStringValue(resId);
        this.routerUrl = routerUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRouterUrl() {
        return routerUrl;
    }

    public void setRouterUrl(String routerUrl) {
        this.routerUrl = routerUrl;
    }
}
