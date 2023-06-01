package com.taopao.oldbase.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.taopao.volleyutils.VolleyUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Ywl on 2016/7/12.
 */
public abstract class YBaseActivity extends Base {

    private static Context mContext;
    public static VolleyUtils app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mContext = this;
        app = VolleyUtils.getInstance(mContext);
//        if (MainActivity.appData == null) {
//            MainActivity.setCacheHelper();
//        }
        if (isApplyEventBus())
            EventBus.getDefault().register(this);
        initView();
        initData();
        initListener();
    }

    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract boolean isApplyEventBus();


    @Override
    protected void onDestroy() {
        if (isApplyEventBus()) EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    public <T> T $(int viewId) {
        return (T) findViewById(viewId);
    }

    public void back(View view) {
        finish();
    }
}
