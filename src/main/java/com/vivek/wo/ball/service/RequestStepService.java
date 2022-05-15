package com.vivek.wo.ball.service;

import com.vivek.wo.ball.ApplicationContextConfig;
import com.vivek.wo.ball.OkHttp3Utils;
import com.vivek.wo.ball.PrintLog;
import com.vivek.wo.ball.PropertiesConfiguration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class RequestStepService {

    private PropertiesConfiguration propertiesConfiguration;

    @Resource
    private OkHttp3Utils okHttp3Utils;

    //异步执行线程池
    private ExecutorService executorService;

    private List<RequestStep> requestStepList = new ArrayList<>();

    public RequestStepService() {
        RequestBillData requestBillData = null;
        propertiesConfiguration = ApplicationContextConfig.applicationContext.getBean(PropertiesConfiguration.class);
        if (!propertiesConfiguration.isDirectOrder()) {
            RequestBillDay requestBillDay = new RequestBillDay();
            requestStepList.add(requestBillDay);

            requestBillData = new RequestBillData();
            requestBillData.relatePreviousRequestStep(requestBillDay);
            requestStepList.add(requestBillData);
        }

        RequestBill requestBill = new RequestBill();
        requestBill.relatePreviousRequestStep(requestBillData);
        requestStepList.add(requestBill);

        RequestBillPay requestBillPay = new RequestBillPay();
        requestBillPay.relatePreviousRequestStep(requestBill);
        requestStepList.add(requestBillPay);

        RequestBillZeroPay requestBillZeroPay = new RequestBillZeroPay();
        requestBillZeroPay.relatePreviousRequestStep(requestBillPay);
        requestStepList.add(requestBillZeroPay);

    }

    public void execute() {
        RequestStep requestStep = null;
        Iterator<RequestStep> iterator = requestStepList.iterator();
        while (iterator.hasNext()) {
            requestStep = iterator.next();
            if (requestStep.isRequestPass()) {
                PrintLog.info("HTTP", "跳过：" + requestStep.requestUrl());
                continue;
            }
            requestStep.resetRetryCount();
            if (requestStep.isAsync() && propertiesConfiguration.isAsyncHttp()) {
                //异步执行线程
                executorService = Executors.newFixedThreadPool(propertiesConfiguration.getRetryCount());
                executeRequestStepAsync(executorService, requestStep);
            } else {
                executeRequestStep(requestStep);
            }

            if (!requestStep.isRequestPass()) {
                break;
            }
        }
        if (requestStep != null) {
            if (requestStep.isRequestPass()) {
                PrintLog.debug("抢购成功");
            } else {
                PrintLog.error("抢购失败");
            }
        }
        //异步关闭线程池
        if (propertiesConfiguration.isAsyncHttp() && executorService != null) {
            executorService.shutdownNow();
            executorService = null;
        }
    }

    //同步请求
    private void executeRequestStep(RequestStep requestStep) {
        while (true) {
            requestStep.execute();
            if (!requestStep.isRequestPass()) {
                if (!requestStep.canRetry()) {
                    PrintLog.info("HTTP", "请求超过次数,流程结束");
                    break;
                }
            } else {
                break;
            }
        }
    }


    //异步请求
    private void executeRequestStepAsync(ExecutorService executorService, RequestStep requestStep) {
        int retryCount = propertiesConfiguration.getRetryCount();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        long intervalTime = propertiesConfiguration.getIntervalTime();
        while (true) {
            executorService.execute(() -> {
                requestStep.execute();
                if (!requestStep.isRequestPass()) {
                    if (!requestStep.canRetry()) {
                        countDownLatch.countDown();
                        PrintLog.info("HTTP", "请求超过次数,流程结束");
                    }
                } else {
                    countDownLatch.countDown();
                }
            });
            //自动模式下退出
            if (countDownLatch.getCount() == 0) {
                break;
            }
            if (!isAutoSetupRetryCountNotLimit()) {
                //手动模式才使用
                retryCount--;
            }
            //手动模式下退出
            if (!isAutoSetupRetryCountNotLimit() && retryCount <= 0) {
                break;
            }
            try {
                Thread.sleep(intervalTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        PrintLog.info("HTTP", "请求完成，开始等待响应过程中");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isAutoSetupRetryCountNotLimit() {
        //自动执行 不受限
        return propertiesConfiguration.isAutoSetup() && !propertiesConfiguration.isAutoSetupCountLimit();
    }
}
