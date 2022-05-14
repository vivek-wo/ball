package com.vivek.wo.ball;

import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class OkHttp3Utils {

    @Resource
    private OkHttpClient okHttpClient;

    @Resource
    private PropertiesConfiguration propertiesConfiguration;

    public Response get(String requestUrl) throws IOException {
        return request(createRequestBuilder()
                .url(requestUrl)
                .get()
                .build());
    }

    public Response post(String requestUrl) throws IOException {
        return request(createRequestBuilder()
                .url(requestUrl)
                .build());
    }

    public Response post(String requestUrl, FormBody formBody) throws IOException {
        return request(createRequestBuilder()
                .url(requestUrl)
                .post(formBody)
                .build());
    }

    private Response request(Request request) throws IOException {
        Call call = okHttpClient.newCall(request);
        addCall(call);
        Response response = call.execute();
        remove(call);
        return response;
    }

    List<Call> cacheCallList = new ArrayList<>();

    void addCall(Call call) {
        cacheCallList.add(call);
    }

    void remove(Call call) {
        cacheCallList.remove(call);
    }

    public void clearCall() {
        Iterator<Call> iterator = cacheCallList.iterator();
        while (iterator.hasNext()) {
            Call call = iterator.next();
            if (call != null) {
                call.cancel();
            }
            PrintLog.debug("取消HTTP请求: " + call);
            iterator.remove();
        }
    }

    private Request.Builder createRequestBuilder() {
        Request.Builder builder = new Request.Builder();
        builder.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36 NetType/WIFI MicroMessenger/7.0.20.1781(0x6700143B) WindowsWechat(0x63060012)");
        builder.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
        builder.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        builder.addHeader("X-Requested-With", "XMLHttpRequest");
        builder.addHeader("Origin", "https://www.chn-hyd.com");
        builder.addHeader("Sec-Fetch-Dest", "empty");
        builder.addHeader("Sec-Fetch-Mode", "cors");
        builder.addHeader("Sec-Fetch-Site", "same-origin");
        builder.addHeader("X-XSRF-TOKEN", propertiesConfiguration.getXsrfToken());
        builder.addHeader("Cookie", propertiesConfiguration.getCookie());
        return builder;
    }
}
