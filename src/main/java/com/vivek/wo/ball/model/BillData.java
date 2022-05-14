package com.vivek.wo.ball.model;

import lombok.Data;

import java.util.List;

@Data
public class BillData {
    //    {
//        "result": [
//        {
//            "canBill": true,
//                "venueType": {
//            "displayName": "市政公园足球场",
//                    "markingPrice": null,
//                    "isBallVenue": false,
//                    "canBuyGoods": false,
//                    "buyGoodsWalletAuthorValue": "[]",
//                    "listBuyGoodsWalletAuthor": [],
//            "isVenueBillNotice": true,
//                    "icon": null,
//                    "cover": null,
//                    "showContentName": null,
//                    "noticeContentName": null,
//                    "noticeText": null,
//                    "bannerName": null,
//                    "description": null,
//                    "payTips": null,
//                    "isPopPayTips": false,
//                    "reportClass": null,
//                    "reportShop": null,
//                    "isVirtual": false,
//                    "billTimeValue": "[{\"isHolidayActive\":false,\"isNotHolidayActive\":true,\"startTime\":1080,\"endTime\":1320,\"billTime\":120},{\"isHolidayActive\":true,\"isNotHolidayActive\":false,\"startTime\":480,\"endTime\":1320,\"billTime\":60}]",
//                    "listBillTime": [
//            {
//                "isHolidayActive": false,
//                    "isNotHolidayActive": true,
//                    "startTime": 1080,
//                    "endTime": 1320,
//                    "billTime": 120
//            },
//            {
//                "isHolidayActive": true,
//                    "isNotHolidayActive": false,
//                    "startTime": 480,
//                    "endTime": 1320,
//                    "billTime": 60
//            }
//                ],
//            "colorFreeString": "#FFFFFF",
//                    "colorDisableString": "#EDEDED",
//                    "colorBookCommonString": "#CBE39A",
//                    "colorBookFixString": "#90D982",
//                    "colorBookTrainString": "#8AE5A8",
//                    "colorWarnString": "#F2EE79",
//                    "colorTimeOutString": "#FA9996",
//                    "colorUseString": "#EACDE3",
//                    "colorPayString": "#57A9FA",
//                    "colorRefundingString": "#AA99FF",
//                    "colorPointString": "#A5D6C0",
//                    "limitRuleValue": "{\"EmpLevelID\":\"00000000-0000-0000-0000-000000000000\",\"BookDay\":1,\"BookDayBefore\":0,\"ViewDay\":1,\"AllowBookTime\":240,\"AllowBookVenue\":0,\"AllowBookTimeByVenue\":120,\"MustBookTimeByVenue\":60}",
//                    "limitRule": {
//                "empLevelID": "00000000-0000-0000-0000-000000000000",
//                        "empLevelDisplayName": null,
//                        "bookDay": 1,
//                        "bookDayBefore": 0,
//                        "viewDay": 1,
//                        "allowBookTime": 240,
//                        "allowBookVenue": 0,
//                        "allowBookTimeByVenue": 120,
//                        "mustBookTimeByVenue": 60,
//                        "canBookStartTime": null,
//                        "canBookEndTime": null,
//                        "supplyTime": null
//            },
//            "empLimitRuleValue": "[{\"EmpLevelID\":\"a29abe6a-73ff-42c4-bfaa-80238162cd4f\",\"EmpLevelDisplayName\":\"普通会员\",\"BookDay\":3,\"BookDayBefore\":0,\"ViewDay\":3,\"AllowBookTime\":120,\"AllowBookVenue\":1,\"AllowBookTimeByVenue\":120,\"MustBookTimeByVenue\":120},{\"EmpLevelID\":\"1c5b5ead-3d55-4609-8340-ffb540c012d7\",\"EmpLevelDisplayName\":\"银卡会员\",\"BookDay\":3,\"BookDayBefore\":0,\"ViewDay\":3,\"AllowBookTime\":120,\"AllowBookVenue\":1,\"AllowBookTimeByVenue\":120,\"MustBookTimeByVenue\":120},{\"EmpLevelID\":\"9cb1e03b-13d0-43c9-9685-5f752e1616d9\",\"EmpLevelDisplayName\":\"金卡会员\",\"BookDay\":3,\"BookDayBefore\":0,\"ViewDay\":3,\"AllowBookTime\":120,\"AllowBookVenue\":1,\"AllowBookTimeByVenue\":120,\"MustBookTimeByVenue\":120},{\"EmpLevelID\":\"93c4a736-65b2-4d7b-b9c3-c3d01ca2600f\",\"EmpLevelDisplayName\":\"钻石会员\",\"BookDay\":3,\"BookDayBefore\":0,\"ViewDay\":3,\"AllowBookTime\":120,\"AllowBookVenue\":1,\"AllowBookTimeByVenue\":120,\"MustBookTimeByVenue\":120}]",
//                    "listEmpLimitRule": [
//            {
//                "empLevelID": "a29abe6a-73ff-42c4-bfaa-80238162cd4f",
//                    "empLevelDisplayName": "普通会员",
//                    "bookDay": 3,
//                    "bookDayBefore": 0,
//                    "viewDay": 3,
//                    "allowBookTime": 120,
//                    "allowBookVenue": 1,
//                    "allowBookTimeByVenue": 120,
//                    "mustBookTimeByVenue": 120,
//                    "canBookStartTime": null,
//                    "canBookEndTime": null,
//                    "supplyTime": null
//            },
//            {
//                "empLevelID": "1c5b5ead-3d55-4609-8340-ffb540c012d7",
//                    "empLevelDisplayName": "银卡会员",
//                    "bookDay": 3,
//                    "bookDayBefore": 0,
//                    "viewDay": 3,
//                    "allowBookTime": 120,
//                    "allowBookVenue": 1,
//                    "allowBookTimeByVenue": 120,
//                    "mustBookTimeByVenue": 120,
//                    "canBookStartTime": null,
//                    "canBookEndTime": null,
//                    "supplyTime": null
//            },
//            {
//                "empLevelID": "9cb1e03b-13d0-43c9-9685-5f752e1616d9",
//                    "empLevelDisplayName": "金卡会员",
//                    "bookDay": 3,
//                    "bookDayBefore": 0,
//                    "viewDay": 3,
//                    "allowBookTime": 120,
//                    "allowBookVenue": 1,
//                    "allowBookTimeByVenue": 120,
//                    "mustBookTimeByVenue": 120,
//                    "canBookStartTime": null,
//                    "canBookEndTime": null,
//                    "supplyTime": null
//            },
//            {
//                "empLevelID": "93c4a736-65b2-4d7b-b9c3-c3d01ca2600f",
//                    "empLevelDisplayName": "钻石会员",
//                    "bookDay": 3,
//                    "bookDayBefore": 0,
//                    "viewDay": 3,
//                    "allowBookTime": 120,
//                    "allowBookVenue": 1,
//                    "allowBookTimeByVenue": 120,
//                    "mustBookTimeByVenue": 120,
//                    "canBookStartTime": null,
//                    "canBookEndTime": null,
//                    "supplyTime": null
//            }
//                ],
//            "isAllowEmpPayByApp": true,
//                    "isNetPayDiscount": true,
//                    "isShowEmpPutMoney": false,
//                    "empPutMoneyText": null,
//                    "empPutMoneyUrl": null,
//                    "empPutMoneyUrlText": null,
//                    "canRefund": true,
//                    "isRefundCheck": false,
//                    "isBeforeRefundNotCheck": false,
//                    "isRefundByDay": false,
//                    "refundTime": 120,
//                    "refundText": null,
//                    "refundTips": null,
//                    "isPopRefundTips": false,
//                    "isOnLineRefundToWallet": false,
//                    "refundWalletID": null,
//                    "canReundLimitByMonth": false,
//                    "canReundBillNumByMonth": 0,
//                    "isPointActive": false,
//                    "empPointRuleID": null,
//                    "empPointRuleDisplayName": null,
//                    "isGrowthActive": false,
//                    "empGrowthRuleID": null,
//                    "empGrowthRuleDisplayName": null,
//                    "partID": null,
//                    "shopID": null,
//                    "partDisplayName": null,
//                    "shopDisplayName": null,
//                    "creationTime": "2021-05-07T11:54:53.313",
//                    "creationUser": "admin[admin]",
//                    "lastModificationTime": "2022-02-24T23:17:50.53",
//                    "lastModificationUser": null,
//                    "remark": "中珠渠南路市政公园",
//                    "id": "737efcc6-c683-4486-8ecd-a45ce530487c"
//        },
//            "limitRule": {
//            "empLevelID": "a29abe6a-73ff-42c4-bfaa-80238162cd4f",
//                    "empLevelDisplayName": "普通会员",
//                    "bookDay": 3,
//                    "bookDayBefore": 0,
//                    "viewDay": 3,
//                    "allowBookTime": 120,
//                    "allowBookVenue": 1,
//                    "allowBookTimeByVenue": 120,
//                    "mustBookTimeByVenue": 120,
//                    "canBookStartTime": null,
//                    "canBookEndTime": null,
//                    "supplyTime": null
//        },
//            "listVenue": [
//            {
//                "isActive": true,
//                    "venueTypeID": "737efcc6-c683-4486-8ecd-a45ce530487c",
//                    "venueTypeDisplayName": "市政公园足球场",
//                    "isVirtual": false,
//                    "isBallVenue": false,
//                    "noticeContentName": null,
//                    "displayName": "7人场",
//                    "isLimitNum": false,
//                    "limitNum": 0,
//                    "isAllowSameVoucher": false,
//                    "isOverTimeFee": false,
//                    "isEndTimeByBill": false,
//                    "allowMinute": null,
//                    "allowEndTime": null,
//                    "allowOutAfter": null,
//                    "overTimePricePlanID": null,
//                    "overTimePricePlanDisplayName": null,
//                    "payOutAfter": null,
//                    "extField1": null,
//                    "creationTime": "2021-05-07T15:19:41.43",
//                    "creationUser": "admin[admin]",
//                    "lastModificationTime": null,
//                    "lastModificationUser": null,
//                    "remark": null,
//                    "id": "bdf6e6a9-ac6e-412c-9ba7-88fdb21db929"
//            }
//            ],
//            "listBillTime": [
//            {
//                "billDay": "2022-05-13T00:00:00+08:00",
//                    "startTime": 1080,
//                    "billTime": 120,
//                    "endTime": 1200,
//                    "timeStartName": "18:00",
//                    "timeEndName": "20:00",
//                    "timeStartEndName": "18:00-20:00"
//            },
//            {
//                "billDay": "2022-05-13T00:00:00+08:00",
//                    "startTime": 1200,
//                    "billTime": 120,
//                    "endTime": 1320,
//                    "timeStartName": "20:00",
//                    "timeEndName": "22:00",
//                    "timeStartEndName": "20:00-22:00"
//            }
//            ],
//            "listVenuePrice": [
//            {
//                "venueTypeID": "737efcc6-c683-4486-8ecd-a45ce530487c",
//                    "billDay": "2022-05-13T00:00:00+08:00",
//                    "startTime": 1080,
//                    "billTime": 120,
//                    "billValue": 0.00,
//                    "realValue": 0.00
//            },
//            {
//                "venueTypeID": "737efcc6-c683-4486-8ecd-a45ce530487c",
//                    "billDay": "2022-05-13T00:00:00+08:00",
//                    "startTime": 1200,
//                    "billTime": 120,
//                    "billValue": 0.00,
//                    "realValue": 0.00
//            }
//            ],
//            "listWeixinVenueStatus": [
//            {
//                "venueID": "bdf6e6a9-ac6e-412c-9ba7-88fdb21db929",
//                    "startTime": "2022-05-13T18:00:00+08:00",
//                    "endTime": "2022-05-13T20:00:00+08:00",
//                    "status": 40,
//                    "remark": "oSuPM0WL-f4GhXOnD7vSSD_aHF8o"
//            },
//            {
//                "venueID": "bdf6e6a9-ac6e-412c-9ba7-88fdb21db929",
//                    "startTime": "2022-05-13T20:00:00+08:00",
//                    "endTime": "2022-05-13T22:00:00+08:00",
//                    "status": 40,
//                    "remark": "oSuPM0c5dSbhCfqj3OUiTGDWwPRw"
//            }
//            ],
//            "listWebVenueStatus": null
//        }
//    ],
//        "targetUrl": null,
//            "success": true,
//            "error": null,
//            "unAuthorizedRequest": false,
//            "__abp": true
//    }
    private boolean canBill;
    private List<BillTime> listBillTime;
    private List<Venue> listVenue;
    private List<VenuePrice> listVenuePrice;

    @Data
    public class Venue {
        private String venueTypeID;
        private String venueTypeDisplayName;
        private String displayName;
        private String id;
    }

    @Data
    public class VenuePrice {
        private String venueTypeID;
        private int startTime;
        private int billTime;
        private double billValue;
        private double realValue;
    }

    @Data
    public class BillTime {
        private String billDay;
        private int startTime;
        private int endTime;
        private int billTime;
        private String timeStartEndName;
    }
}
