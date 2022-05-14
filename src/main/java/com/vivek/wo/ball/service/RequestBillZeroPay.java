package com.vivek.wo.ball.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.vivek.wo.ball.PrintLog;
import com.vivek.wo.ball.model.Bill;
import com.vivek.wo.ball.model.BillDay;
import com.vivek.wo.ball.model.Result;
import okhttp3.FormBody;
import okhttp3.Response;

import java.io.IOException;

public class RequestBillZeroPay extends RequestStep<Result<String>> {

    @Override
    String requestUrl() {
        return "https://www.chn-hyd.com/HTS6/api/services/app/WeixinBillVenue/VenueBillPayByZeroAsync";
    }

    String getBillRecordNo() {
        RequestStep<Result<String>> billStep = getPreviousRequestStep();
        return ((RequestBillPay) billStep).getBillRecordNo();
    }

    @Override
    void executeHttp() {
        try {
            FormBody formBody = new FormBody.Builder()
                    .add("value", getBillRecordNo())
                    .add("webApiUniqueID", getWebApiUniqueID())
                    .build();
            Response response = okHttp3Utils.post(requestUrl(), formBody);
            String printMessage = "";
            if (response.isSuccessful()) {
                String bodyJson = response.body().string();
                printResponseBody(bodyJson);
                result = JSON.parseObject(bodyJson,
                        new TypeReference<Result<String>>() {
                        });
                if (result != null) {
                    if (result.isSuccess()) {
                        setRequestPass(true);
                        printMessage = "成功";
                    } else {
                        printMessage = result.getError();
                    }
                }
            } else {
                printMessage = response.body().string();
            }
            PrintLog.debug("[HTTP] " + response.code() + " " + printMessage);
        } catch (IOException e) {
            PrintLog.debug("[ERROR] HTTP IOException " + e.getMessage());
        }
    }
}
