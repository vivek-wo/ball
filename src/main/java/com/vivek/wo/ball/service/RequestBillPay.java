package com.vivek.wo.ball.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.vivek.wo.ball.PrintLog;
import com.vivek.wo.ball.model.*;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RequestBillPay extends RequestStep<Result<String>> {

    @Override
    String requestUrl() {
        return "https://www.chn-hyd.com/HTS6/AppVenue/VenueBill/VenueBillPay";
    }

    public String getBillRecordNo() {
        RequestStep<Result<Bill>> billStep = getPreviousRequestStep();
        return billStep.result.getResult().getBillRecordNo();
    }

    @Override
    void executeHttp() {
        try {
            Response response = okHttp3Utils.get(requestUrl() + "?BillRecordNo=" + getBillRecordNo());
            String printMessage = "";
            if (response.isSuccessful()) {
                String bodyJson = response.body().string();
                printResponseBody(bodyJson);
                setRequestPass(true);
            } else {
                printMessage = response.body().string();
            }
            PrintLog.debug("[HTTP] " + response.code() + " " + printMessage);
        } catch (IOException e) {
            PrintLog.debug("[ERROR] HTTP IOException " + e.getMessage());
        }
    }
}
