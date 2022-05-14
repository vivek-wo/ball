package com.vivek.wo.ball;

import com.vivek.wo.ball.service.RequestStepService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

@Service
public class BallMain {

    @Resource
    private RequestStepService requestStepService;

    @Resource
    private PropertiesConfiguration propertiesConfiguration;

    public PropertiesConfiguration getPropertiesConfiguration() {
        return propertiesConfiguration;
    }

    static CountDownLatch countDownLatch;

    //1. 主函数
    public void ballMain() {
        //读取配置文件
        if (checkEmpty(propertiesConfiguration.getCookie()) || checkEmpty(propertiesConfiguration.getXsrfToken())) {
            PrintLog.info("MAIN", "无法加载登录信息");
            return;
        }
        PrintLog.info("MAIN", "加载登录信息");
        PrintLog.debug("cookie: " + propertiesConfiguration.getCookie());
        PrintLog.debug("X-XSRF_Token: " + propertiesConfiguration.getXsrfToken());

        //判断模式
        PrintLog.info("MAIN",
                (getPropertiesConfiguration().isAutoSetup() ? "自动模式" : "手动模式，请按下Enter键开始"));
        if (getPropertiesConfiguration().isAutoSetup()) {
            countDownLatch = new CountDownLatch(1);
            SystemTiming.setupSystemTiming();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
            }
            //执行HTTP函数
            executeMainHttp();
        } else {
            String inputValue = "";
            do {
                //手动
                PrintLog.info("MAIN", "监听按键输入");
                Scanner scanner = new Scanner(System.in);
                inputValue = scanner.nextLine();
                if (inputValue.trim().equalsIgnoreCase("Q")) {
                    break;
                }
                //执行HTTP函数
                executeMainHttp();
            } while (true);
        }

        PrintLog.info("MAIN", "停止执行.");
    }

    //执行HTTP请求
    private void executeMainHttp() {
        requestStepService.execute();
    }

    private static boolean checkEmpty(String text) {
        return text == null || text.isEmpty();
    }

    //系统计时器
    static class SystemTiming {

        public static Thread setupSystemTiming() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Thread thread = new Thread(() -> {
                //获取0点时间
                long zeroTime = getZeroTime();
                PrintLog.info("MAIN", "零点时间：" + zeroTime);
                try {
                    do {
                        String time = "Current System Time: " + dateFormat.format(new Date());
                        System.out.print(generate(time.length(), '\b'));
                        System.out.print(time);
                        if (System.currentTimeMillis() >= zeroTime) {
                            Thread.currentThread().interrupt();
                        }
                        Thread.sleep(10);
                    } while (!Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                }
                System.out.println("");
                PrintLog.info("MAIN", "零点时间到，自动执行HTTP请求");
                countDownLatch.countDown();
            });
            thread.start();
            return thread;
        }

        private static String generate(int num, char ch) {
            if (num == 0) return "";
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < num; i++) {
                builder.append(ch);
            }
            return builder.toString();
        }

        private static long getZeroTime() {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTimeInMillis();
        }
    }
}
