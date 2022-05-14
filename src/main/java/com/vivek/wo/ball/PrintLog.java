package com.vivek.wo.ball;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.fusesource.jansi.Ansi.ansi;

@Slf4j
public class PrintLog {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void info(String tag, String l) {
//        log.info(dateFormat.format(new Date()) + " [" + tag + "] " + l);
        System.out.println("\n" + dateFormat.format(new Date()) + " <" + Thread.currentThread().getId() + "> [" + tag + "] " + l);
    }

    public static void debug(String l) {
//        log.info(ansi().eraseLine().fgGreen().a(dateFormat.format(new Date()) + " " + l).reset().toString());
        System.out.println(ansi().eraseLine().fgGreen().a(dateFormat.format(new Date()) + " <" + Thread.currentThread().getId() + "> " + l).reset());
    }

    public static void error(String l) {
//        log.info(ansi().eraseLine().fgRed().a(dateFormat.format(new Date()) + " " + l).reset().toString());
        System.out.println(ansi().eraseLine().fgRed().a(dateFormat.format(new Date()) + " <" + Thread.currentThread().getId() + "> " + l).reset());
    }
}
