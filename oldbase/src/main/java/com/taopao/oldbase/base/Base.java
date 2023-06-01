package com.taopao.oldbase.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ywl on 2016/7/12.
 */
public abstract class Base extends AppCompatActivity {
    public ProgressDialog pd;
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();

    public enum TransitionMode {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        SCALE,
        FADE,
        CARD
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (null != intent) {
            startAnim();
            pd = new ProgressDialog(this);
        }
        super.onCreate(savedInstanceState);
    }

    private void startAnim() {
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    //    protected abstract boolean toggleOverridePendingTransition();
    protected abstract TransitionMode getOverridePendingTransitionMode();

    @Override
    public void finish() {
        super.finish();
        startAnim();
    }

    public void setProgressDialog(String msg) {
        pd.setCancelable(true);
        pd.setMessage(msg);
        if (!isFinishing())
            pd.show();
    }

    public void setProgressDialog(String msg, boolean is) {
        pd.setCancelable(is);
        pd.setMessage(msg);
        if (!isFinishing())
            pd.show();
    }

    public void dialogDismiss() {
        if (!isFinishing() && pd != null && pd.isShowing())
            pd.dismiss();
    }

    public void dismissProgressDialog() {
        if (!isFinishing() && pd != null && pd.isShowing())
            pd.dismiss();
    }

    // 判断权限集合
    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(getApplicationContext(), permission) ==
                PackageManager.PERMISSION_DENIED;
    }

    public boolean checkPermission(String[] permissions, int REQUEST_FOR_PERMISSIONS) {
        if (lacksPermissions(permissions)) {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    REQUEST_FOR_PERMISSIONS);
            return true;
        }
        return false;
    }

}
