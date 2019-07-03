package cn.luo.android.quick.ui;

import android.annotation.SuppressLint;
import android.os.Handler;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SDCardUtils;

import cn.luo.android.quick.ConfigConstants;
import cn.luo.android.quick.library.DefaultSplashActivity;
import cn.luo.android.quick.util.RouterUtils;

public class SplashActivity extends DefaultSplashActivity {

    @SuppressLint("MissingPermission")
    @Override
    protected void doAfterGranted() {
        initDir();
        CrashUtils.init(ConfigConstants.PATH_CRASH_DIR);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RouterUtils.toMainActivity();
                finish();
            }
        }, 1000);
    }

    private void initDir() {
        FileUtils.createOrExistsDir(ConfigConstants.PATH_APP_DIR);
        LogUtils.d(ConfigConstants.PATH_APP_DIR);
        FileUtils.createOrExistsDir(ConfigConstants.PATH_CRASH_DIR);
    }

    @Override
    protected String[] getRequestPermissions() {
        return new String[]{PermissionConstants.STORAGE};
    }
}
