package com.vivek.wo.ball;

import org.fusesource.jansi.AnsiConsole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class BallApplication {

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        PrintLog.info("MAIN", "启动本地服务");
        ConfigurableApplicationContext context = SpringApplication.run(BallApplication.class, args);
        BallMain ballMain = ApplicationContextConfig.applicationContext.getBean(BallMain.class);
        ballMain.ballMain();
        context.close();
        PrintLog.info("MAIN", "停止本地服务");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
