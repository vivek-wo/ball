package com.vivek.wo.ball.model;

import lombok.Data;

@Data
public class Result<T> {
    //    {
//        "result": {
//        "startDay": "2022-05-11T00:00:00+08:00",
//                "endDay": "2022-05-13T00:00:00+08:00"
//    },
//        "targetUrl": null,
//            "success": true,
//            "error": null,
//            "unAuthorizedRequest": false,
//            "__abp": true
//    }
    T result;
    private String targetUrl;
    private boolean success;
    private String error;
    private boolean unAuthorizedRequest;
    private boolean __abp;

}
