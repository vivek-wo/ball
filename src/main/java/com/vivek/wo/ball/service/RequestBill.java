package com.vivek.wo.ball.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.vivek.wo.ball.PrintLog;
import com.vivek.wo.ball.model.Bill;
import com.vivek.wo.ball.model.BillData;
import com.vivek.wo.ball.model.Result;
import okhttp3.FormBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class RequestBill extends RequestStep<Result<Bill>> {

    @Override
    String requestUrl() {
        return "https://www.chn-hyd.com/HTS6/api/services/app/WeixinBillVenue/VenueBillAsync";
    }

    Result<List<BillData>> getBillData() {
        RequestStep<Result<List<BillData>>> billDataStep = getPreviousRequestStep();
        return billDataStep.result;
    }

    String getBillDay() {
        return getBillData().getResult().get(0).getListBillTime()
                .get(propertiesConfiguration.getBillTime()).getBillDay();
    }

    String getTimeStartEndName() {
        return getBillData().getResult().get(0).getListBillTime()
                .get(propertiesConfiguration.getBillTime()).getTimeStartEndName();
    }

    String getVenueTypeDisplayName() {
        return getBillData().getResult().get(0).getListVenue().get(0).getVenueTypeDisplayName();
    }

    String getVenueDisplayName() {
        return getBillData().getResult().get(0).getListVenue().get(0).getDisplayName();
    }

    int getEndTime() {
        return getBillData().getResult().get(0).getListBillTime()
                .get(propertiesConfiguration.getBillTime()).getEndTime();
    }

    int getStartTime() {
        return getBillData().getResult().get(0).getListBillTime()
                .get(propertiesConfiguration.getBillTime()).getStartTime();
    }

    int getBillTime() {
        return getBillData().getResult().get(0).getListBillTime()
                .get(propertiesConfiguration.getBillTime()).getBillTime();
    }

    String getVenueID() {
        return getBillData().getResult().get(0).getListVenue().get(0).getId();
    }

    @Override
    boolean isAsync() {
        return true;
    }

    @Override
    void executeHttp() {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            if (propertiesConfiguration.isDirectOrder()) {
                PrintLog.debug("直接锁定日期：" + propertiesConfiguration.getBillDay());
                builder.add("billDay", propertiesConfiguration.getBillDay());
                builder.add("webApiUniqueID", getWebApiUniqueID());
                builder.add("listData[0][endTime]", "" + propertiesConfiguration.getEndTime());
                builder.add("listData[0][startTime]", "" + propertiesConfiguration.getStartTime());
                PrintLog.debug("抢购时间：0场次 " + (propertiesConfiguration.getStartTime()) + " " + (propertiesConfiguration.getEndTime()));
                builder.add("listData[0][venueID]", propertiesConfiguration.getVenueID());
                builder.add("listData[0][billValue]", "0");
                builder.add("listData[0][realValue]", "0");
                builder.add("listData[0][venueDisplayName]", propertiesConfiguration.getVenueDisplayName());
                builder.add("listData[0][venueTypeDisplayName]", propertiesConfiguration.getVenueTypeDisplayName());
            } else {
                PrintLog.debug("锁定日期：" + getBillDay() + "  开始时间：" + getTimeStartEndName());
                builder.add("billDay", getBillDay());
                builder.add("webApiUniqueID", getWebApiUniqueID());
                int billTime = getBillTime();
                int count = billTime == 60 ? 2 : 1;
                for (int i = 0; i < count; i++) {
                    builder.add("listData[" + i + "][endTime]", "" + (getEndTime() + i * billTime));
                    builder.add("listData[" + i + "][startTime]", "" + (getStartTime() + i * billTime));
                    PrintLog.debug("抢购时间：" + i + "场次 " + (getStartTime() + i * billTime) + " " + (getEndTime() + i * billTime));
                    builder.add("listData[" + i + "][venueID]", getVenueID());
                    builder.add("listData[" + i + "][billValue]", "0");
                    builder.add("listData[" + i + "][realValue]", "0");
                    builder.add("listData[" + i + "][venueDisplayName]", getVenueDisplayName());
                    builder.add("listData[" + i + "][venueTypeDisplayName]", getVenueTypeDisplayName());
                }
            }
            Response response = okHttp3Utils.post(requestUrl(), builder.build());
            String printMessage = "";
            if (response.isSuccessful()) {
                String bodyJson = response.body().string();
                printResponseBody(bodyJson);
                result = JSON.parseObject(bodyJson,
                        new TypeReference<Result<Bill>>() {
                        });
                if (result != null) {
                    if (result.isSuccess()) {
                        setRequestPass(true);
                        printMessage = "锁定场次：" + result.getResult().getBillRecordNo();
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
