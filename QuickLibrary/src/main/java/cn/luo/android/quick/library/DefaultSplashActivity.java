package cn.luo.android.quick.library;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.UtilsTransActivity;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;

import java.util.List;

import cn.luo.android.quick.library.base.BaseActivity;

/**
 * @author Hurston
 * @version 1.0.0
 * @description
 * @createdTime 2018/11/28 17:02
 * @note
 */
public class DefaultSplashActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return -1;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initBeforeView() {
        setTheme(getSplashTheme());

        String[] permissions = getRequestPermissions();
        if (permissions == null || permissions.length == 0) {
            doAfterGranted();
        } else {
            PermissionUtils.permission(permissions)
                    .rationale(new PermissionUtils.OnRationaleListener() {
                        @Override
                        public void rationale(UtilsTransActivity activity, final ShouldRequest shouldRequest) {
                            new XPopup.Builder(context).asConfirm(getString(android.R.string.dialog_alert_title),
                                    getString(R.string.permission_rationale_message),
                                    getString(android.R.string.cancel),
                                    getString(android.R.string.ok), new OnConfirmListener() {
                                        @Override
                                        public void onConfirm() {
                                            shouldRequest.again(true);
                                        }
                                    }, new OnCancelListener() {
                                        @Override
                                        public void onCancel() {
                                            shouldRequest.again(false);
                                        }
                                    }, false)
                                    .show();
                        }
                    })
                    .callback(new PermissionUtils.FullCallback() {
                        @Override
                        public void onGranted(List<String> permissionsGranted) {
                            doAfterGranted();
                        }

                        @Override
                        public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                            new XPopup.Builder(context).asConfirm(getString(android.R.string.dialog_alert_title),
                                    getString(R.string.permission_denied_forever_message),
                                    getString(android.R.string.cancel),
                                    getString(android.R.string.ok), new OnConfirmListener() {
                                        @Override
                                        public void onConfirm() {
                                            PermissionUtils.launchAppDetailsSettings();
                                        }
                                    }, new OnCancelListener() {
                                        @Override
                                        public void onCancel() {
                                        }
                                    }, false)
                                    .show();
                        }
                    })
                    .theme(new PermissionUtils.ThemeCallback() {
                        @Override
                        public void onActivityCreate(Activity activity) {
                            ScreenUtils.setFullScreen(activity);
                        }
                    })
                    .request();
        }
    }

    protected int getSplashTheme() {
        return R.style.SplashTheme;
    }

    protected void doAfterGranted() {

    }

    protected String[] getRequestPermissions() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initAfterView() {

    }

    @Override
    protected int getFinishType() {
        return 0;
    }
}
