package com.taopao.oldbase.base;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.taopao.volleyutils.VolleyUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Lkn on 2016/7/15.
 */
public abstract class BaseFragment extends Fragment {
    public static VolleyUtils app;
    public ProgressDialog progressDialog;
    public View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (MainActivity.appData == null){
//            MainActivity.setCacheHelper();
//        }
        app = VolleyUtils.getInstance(getActivity());
        if (isApplyEventBus()) EventBus.getDefault().register(this);
        getIntent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(layoutId(), container, false);
            progressDialog = new ProgressDialog(getActivity());
            initView(view);
            initData();
            initListener();
        }
        return view;
    }

    protected abstract void getIntent();

    protected abstract int layoutId();

    protected abstract void initView(View v);

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract boolean isApplyEventBus();

    @Override
    public void onDestroy() {
        if (isApplyEventBus()) EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) view.getParent()).removeView(view);
    }

    //映射view的快捷方法
    public <T> T $(View v, int viewId) {
        return (T) v.findViewById(viewId);
    }

    public void setProgressDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
        }

        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (getActivity() == null) {
            return;
        }
        if (!getActivity().isFinishing() && progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
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
        return ContextCompat.checkSelfPermission(getActivity(), permission) ==
                PackageManager.PERMISSION_DENIED;
    }

    public boolean checkPermission(String[] permissions, int REQUEST_FOR_PERMISSIONS) {
        if (lacksPermissions(permissions)) {
            ActivityCompat.requestPermissions(getActivity(),
                    permissions,
                    REQUEST_FOR_PERMISSIONS);
            return true;
        }
        return false;
    }
}
