package com.vivek.wo.ball;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ball")
public class PropertiesConfiguration {
    private String cookie;
    private String venueTypeID;
    private String xsrfToken;
    private boolean printResponseBody;
    private int billTime;
    private boolean autoSetup;
    private boolean autoSetupCountLimit;
    private long intervalTime = 200;
    private boolean asyncHttp;
    private int retryCount = 5;

    //直接订场配置
    private boolean directOrder;
    private int startTime;
    private int endTime;
    private String billDay;
    private String venueID;
    private String venueDisplayName;
    private String venueTypeDisplayName;
}
