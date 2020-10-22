package com.jike.jiujing.common.service;

import android.util.Log;

import com.jike.jiujing.common.utils.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.Utf8;

public class LogInterceptor implements Interceptor {
    private static final String TAG = "Retrofit";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.v(TAG, "request:" + request.toString());
        long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        Log.v(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        okhttp3.MediaType mediaType = response.body().contentType();


        //请求参数
        RequestBody requestBody= request.body();
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        Charset charset = Charset.forName("UTF-8");
        MediaType contentType = requestBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(Charset.forName("UTF-8"));
        }
        String paramsStr = buffer.readString(charset);
        LogUtils.show(TAG, "param body:\n" + paramsStr);


        String content = response.body().string();
        LogUtils.show(TAG, "response body:\n" + content);
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}
