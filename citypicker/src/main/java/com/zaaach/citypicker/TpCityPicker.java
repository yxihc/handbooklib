package com.zaaach.citypicker;

import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @Author: Bro0cL
 * @Date: 2018/2/6 17:52
 */
public class TpCityPicker {
    private static final String TAG = "CityPicker";

    private WeakReference<FragmentActivity> mContext;
    private WeakReference<Fragment> mFragment;
    private WeakReference<FragmentManager> mFragmentManager;

    private boolean enableAnim;
    private int mAnimStyle;
    private LocatedCity mLocation;
    private List<HotCity> mHotCities;
    private OnPickListener mOnPickListener;

    private TpCityPicker(){}

    private TpCityPicker(Fragment fragment){
        this(fragment.getActivity(), fragment);
        mFragmentManager = new WeakReference<>(fragment.getChildFragmentManager());
    }

    private TpCityPicker(FragmentActivity activity){
        this(activity, null);
        mFragmentManager = new WeakReference<>(activity.getSupportFragmentManager());
    }

    private TpCityPicker(FragmentActivity activity, Fragment fragment){
        mContext = new WeakReference<>(activity);
        mFragment = new WeakReference<>(fragment);
    }

    public static TpCityPicker from(Fragment fragment){
        return new TpCityPicker(fragment);
    }

    public static TpCityPicker from(FragmentActivity activity){
        return new TpCityPicker(activity);
    }

    /**
     * 设置动画效果
     * @param animStyle
     * @return
     */
    public TpCityPicker setAnimationStyle(@StyleRes int animStyle) {
        this.mAnimStyle = animStyle;
        return this;
    }

    /**
     * 设置当前已经定位的城市
     * @param location
     * @return
     */
    public TpCityPicker setLocatedCity(LocatedCity location) {
        this.mLocation = location;
        return this;
    }

    public TpCityPicker setHotCities(List<HotCity> data){
        this.mHotCities = data;
        return this;
    }

    /**
     * 启用动画效果，默认为false
     * @param enable
     * @return
     */
    public TpCityPicker enableAnimation(boolean enable){
        this.enableAnim = enable;
        return this;
    }

    /**
     * 设置选择结果的监听器
     * @param listener
     * @return
     */
    public TpCityPicker setOnPickListener(OnPickListener listener){
        this.mOnPickListener = listener;
        return this;
    }

    public void show(){
        FragmentTransaction ft = mFragmentManager.get().beginTransaction();
        final Fragment prev = mFragmentManager.get().findFragmentByTag(TAG);
        if (prev != null){
            ft.remove(prev).commit();
            ft = mFragmentManager.get().beginTransaction();
        }
        ft.addToBackStack(null);
        final TpCityPickerDialogFragment cityPickerFragment =
                TpCityPickerDialogFragment.newInstance(enableAnim);
        cityPickerFragment.setLocatedCity(mLocation);
        cityPickerFragment.setHotCities(mHotCities);
        cityPickerFragment.setAnimationStyle(mAnimStyle);
        cityPickerFragment.setOnPickListener(mOnPickListener);
        cityPickerFragment.show(ft, TAG);
    }

    /**
     * 定位完成
     * @param location
     * @param state
     */
    public void locateComplete(LocatedCity location, @LocateState.State int state){
        TpCityPickerDialogFragment fragment = (TpCityPickerDialogFragment) mFragmentManager.get().findFragmentByTag(TAG);
        if (fragment != null){
            fragment.locationChanged(location, state);
        }
    }
}
