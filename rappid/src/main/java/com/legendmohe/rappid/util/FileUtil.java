package com.legendmohe.rappid.util;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by legendmohe on 16/6/6.
 */
public class FileUtil {
    public static byte[] fullyReadFileToBytes(File f) throws IOException {
        int size = (int) f.length();
        byte bytes[] = new byte[size];
        byte tmpBuff[] = new byte[size];
        FileInputStream fis = new FileInputStream(f);
        ;
        try {

            int read = fis.read(bytes, 0, size);
            if (read < size) {
                int remain = size - read;
                while (remain > 0) {
                    read = fis.read(tmpBuff, 0, remain);
                    System.arraycopy(tmpBuff, 0, bytes, size - remain, read);
                    remain -= read;
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            fis.close();
        }

        return bytes;
    }

    public static String generateDatePath(String rootPath, String suffix) throws IOException {
        if (TextUtils.isEmpty(rootPath)) {
            return null;
        }

        // 创建根目录
        File file = new File(rootPath);
        if (!file.exists()) {
            if (!file.mkdir()) {
                return null;
            }
        }

        Calendar calendar = Calendar.getInstance();

        // 年、月、日
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String filePath = String.format("%s/%04d%02d%02d", rootPath, year, month, day);
        file = new File(filePath);
        if (!file.exists()) {
            if (!file.mkdir()) {
                return null;
            }
        }

        // 年、月、日、时、分、秒
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        int milsec = calendar.get(Calendar.MILLISECOND);

        // 文件格式为mnt/sdcard/VideoGo/20120901/20120901141138540_test.jpg
        filePath += String.format("/%04d%02d%02d%02d%02d%02d%03d_%s", year, month, day, hour, min, sec, milsec, suffix);
        return filePath;
    }
}
