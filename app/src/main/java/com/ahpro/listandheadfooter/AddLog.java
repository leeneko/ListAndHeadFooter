package com.ahpro.listandheadfooter;

import android.icu.text.SimpleDateFormat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class AddLog {
    public static void info(String method, String text) {
        writeFile(method, "[I]", text);
    }

    public static void error(String method, String text) {
        writeFile(method, "[E]", text);
    }

    public static void warning(String method, String text) {
        writeFile(method, "[W]", text);
    }

    private static void writeFile(String method, String type, String text) {
        try {
            File dir = new File(AppSettings.logPath);

            if (!dir.exists()) {
                dir.mkdir();
            }

            SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
            Date time1 = new Date();
            String sTime1 = format1.format(time1);
            String logFile = AppSettings.logPath + "log_" + sTime1 + ".txt";
            File file = new File(logFile);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            // 로그 헤더 구분자(Error)
            pr.print(type + " ");
            // 로그 헤더 시분초
            SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS");
            Date time2 = new Date();
            String sTime2 = format2.format(time2);
            pr.print(sTime2 + " ");
            // 발생한 메소드
            pr.println(method);
            pr.println(text);
            pr.close();
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
