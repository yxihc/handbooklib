package com.taopao.volleyutils;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.taopao.volleyutils.volley.BaseRequest;

import java.util.Map;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

public class VolleyUtils {
    public static void init() {
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }

    private Context mContext;
    private static VolleyUtils mVolleyUtils;

    private VolleyUtils(Context context) {
        mContext = context;
    }

    public static VolleyUtils getInstance(Context context) {
        if (mVolleyUtils == null) {
            mVolleyUtils = new VolleyUtils(context);
        }
        return mVolleyUtils;
    }


    private RequestQueue mQueue;
    private DefaultRetryPolicy mPolicy;

    /**
     * 将请求加入队列并执行，默认不进行请求缓存，默认6秒超时，retry一次
     *
     * @param what
     * @param request
     * @param tag
     */
    public void addRequestQueue(int what, BaseRequest<?> request, Object tag) {
        addRequestQueue(what, request, false, tag);
    }

    /**
     * 默认6秒超时，retry一次
     *
     * @param what
     * @param request
     * @param shouldCache
     */
    public void addRequestQueue(int what, BaseRequest<?> request,
                                boolean shouldCache) {
        addRequestQueue(what, request, shouldCache, null);
    }

    /**
     * 默认6秒超时，retry一次
     *
     * @param what
     * @param request
     * @param shouldCache
     * @param tag
     */
    public void addRequestQueue(int what, BaseRequest<?> request,
                                boolean shouldCache, Object tag) {
        addRequestQueue(what, request, shouldCache, 10 * 1000, false, tag);
    }

    public void addRequestQueue2(int what, BaseRequest<?> request,
                                 boolean shouldCache, Object tag) {
        addRequestQueue(what, request, shouldCache, 20 * 1000, true, tag);
    }

    public void addRequestQueue(int what, BaseRequest<?> request,
                                boolean shouldCache, int initialTimeoutMs, boolean retry, Object tag) {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(mContext);
        }
        if (mPolicy == null) {
            mPolicy = new DefaultRetryPolicy(initialTimeoutMs, retry ? 9 : 0,
                    1.0f);
        }
//        request.putHeader(getMap());
        request.setRetryPolicy(mPolicy);
        request.setWhat(what);
        request.setShouldCache(shouldCache);
        if (tag != null)
            request.setTag(tag);
        mQueue.add(request);
    }


    public static String _MakeURL(String p_url, Map<String, Object> params) {
        StringBuilder url = new StringBuilder(p_url);
        if (url.indexOf("?") < 0)
            url.append('?');
        for (String name : params.keySet()) {
            url.append('&');
            url.append(name);
            url.append('=');
            url.append(String.valueOf(params.get(name)));
            // 不做URLEncoder处理n
            // url.append(URLEncoder.encode(String.valueOf(params.get(name)),
            // UTF_8));
        }
        return url.toString().replace("?&", "?");
    }


}
