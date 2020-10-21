package com.jike.jiujing.common.service;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    public static final String AUTH_HEADER = "MIGOZI-AUTH";

    private Context context;

    public HeaderInterceptor(Context context) {
        this.context = context;
    }

    /**
     * intercept server api to add default params
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        Request.Builder builder = request.newBuilder()
                .url(urlBuilder.build());
        builder.addHeader("client_id", "84L1FJTUm5NIO57091l32DKa7Z76e4139x9aeBi5S902AX4JBtb77q48jL5");
        return chain.proceed(builder.build());
    }
}
