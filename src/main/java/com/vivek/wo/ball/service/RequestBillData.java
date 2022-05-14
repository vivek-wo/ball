package com.vivek.wo.ball.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.vivek.wo.ball.PrintLog;
import com.vivek.wo.ball.model.BillData;
import com.vivek.wo.ball.model.BillDay;
import com.vivek.wo.ball.model.Result;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class RequestBillData extends RequestStep<Result<List<BillData>>> {

    @Override
    String requestUrl() {
        return "https://www.chn-hyd.com/HTS6/api/services/app/VenueBill/GetVenueBillDataAsync";
    }

    String getBillDay() {
        RequestStep<Result<BillDay>> billDayResult = getPreviousRequestStep();
        return billDayResult.result.getResult().getEndDay();
    }

    @Override
    void executeHttp() {
        try {
            FormBody formBody = new FormBody.Builder()
                    .add("VenueTypeID", getVenueTypeID())
                    .add("webApiUniqueID", getWebApiUniqueID())
                    .add("IsGetPrice", "true")
                    .add("isApp", "true")
                    .add("billDay", getBillDay())
                    .build();
            Response response = okHttp3Utils.post(requestUrl(), formBody);
            String printMessage = "";
            if (response.isSuccessful()) {
                String bodyJson = response.body().string();
                printResponseBody(bodyJson);
                result = JSON.parseObject(bodyJson,
                        new TypeReference<Result<List<BillData>>>() {
                        });
                if (result != null) {
                    if (result.isSuccess()
                            && result.getResult().size() > 0) {
                        setRequestPass(true);
                        List<BillData.Venue> venueList = result.getResult().get(0).getListVenue();
                        if (venueList.size() > 0) {
                            printMessage = venueList.get(0).getVenueTypeDisplayName()
                                    + ", " + venueList.get(0).getDisplayName()
                                    + ", " + venueList.get(0).getId();
                        }
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
