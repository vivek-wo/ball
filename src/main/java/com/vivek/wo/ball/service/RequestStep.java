package com.vivek.wo.ball.service;

import com.vivek.wo.ball.ApplicationContextConfig;
import com.vivek.wo.ball.OkHttp3Utils;
import com.vivek.wo.ball.PrintLog;
import com.vivek.wo.ball.PropertiesConfiguration;

import java.util.UUID;

public abstract class RequestStep<T> {
    T result;
    private RequestStep previousRequestStep;
    //重试次数
    private int retryCount = 3;
    //请求是否通过
    private volatile boolean requestPass = false;
    PropertiesConfiguration propertiesConfiguration;
    OkHttp3Utils okHttp3Utils;

    RequestStep() {
        okHttp3Utils = ApplicationContextConfig.applicationContext.getBean(OkHttp3Utils.class);
        propertiesConfiguration = ApplicationContextConfig.applicationContext.getBean(PropertiesConfiguration.class);
        resetRetryCount();
    }

    RequestStep relatePreviousRequestStep(RequestStep requestStep) {
        previousRequestStep = requestStep;
        return this;
    }

    public <FT> RequestStep<FT> getPreviousRequestStep() {
        return previousRequestStep;
    }

    void execute() {
        PrintLog.info("HTTP", "请求 " + requestUrl());
        executeHttp();
        //后置，支持异步请求判断
        retryCount--;
    }

    abstract void executeHttp();

    //是否需要异步请求，优先级大于配置文件
    boolean isAsync() {
        return false;
    }

    void resetRetryCount() {
        retryCount = propertiesConfiguration.getRetryCount();
    }

    void printResponseBody(String bodyJson) {
        if (propertiesConfiguration.isPrintResponseBody()) {
            PrintLog.debug(bodyJson);
        }
    }

    boolean canRetry() {
        return retryCount > 0 || (propertiesConfiguration.isAutoSetup() && !propertiesConfiguration.isAutoSetupCountLimit());
    }

    public void setRequestPass(boolean requestPass) {
        this.requestPass = requestPass;
    }

    public boolean isRequestPass() {
        return requestPass;
    }

    String requestUrl() {
        return "";
    }

    String getWebApiUniqueID() {
        return UUID.randomUUID().toString();
    }

    String getVenueTypeID() {
        return propertiesConfiguration.getVenueTypeID();
    }
}