package com.zhilian.market.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;

import java.io.*;

/**
 * @author one
 * Created by Link on 2017/9/8.
 */
public class StreamUtil {
    public static ByteArrayOutputStream cacheInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream streamCache = new ByteArrayOutputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        byte[] b = new byte[1024];

        for (int c ; (c = bis.read(b)) != -1;) {
            streamCache.write(b, 0, c);
        }

        return streamCache;
    }

    public static boolean storeToLocal(File file,InputStream is) {
        if(!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);

                byte buff[] = new byte[1024];
                int read;

                while ((read = is.read(buff, 0, 1024)) != -1) {
                    fos.write(buff, 0, read);
                    fos.flush();
                }
                safeClose(is);
                safeClose(fos);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;

    }


    public static boolean storeToLocal(File file,byte[] data) {
        return storeToLocal(file,new ByteArrayInputStream(data));
    }

    public static byte[] fileData(File file) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        FileInputStream fis = new FileInputStream(file);

        byte buffer[] = new byte[1024];
        int read;

        while ((read = fis.read(buffer, 0, 1024)) != -1) {
            bos.write(buffer, 0, read);
            bos.flush();
        }

        safeClose(fis);

        return bos.toByteArray();
    }

    public static byte[] fromStream(InputStream is) throws IOException {
        byte buffer[] = null;
        int read;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            buffer = new byte[1024];
            while ( (read = is.read(buffer,0,1024)) != -1 ) {
                bos.write(buffer,0,read);
                bos.flush();
            }
            buffer = bos.toByteArray();
        }

        return buffer;
    }

    public static String tmpFilePath() {
        return System.getProperty("java.io.tmpdir") + File.separator + "tmp" + RandomStringUtils.random(32,true,true);
    }

    public static long inToOut(InputStream is,OutputStream os) throws IOException {
        long totalLen = 0;
        try {
            byte buffer[] = new byte[1024];
            int len;

            while ( (len = is.read(buffer,0,1024) ) != -1 ) {
                os.write(buffer,0,len);
                os.flush();

                totalLen += len;
            }
        } finally {
            safeClose(is);
            safeClose(os);
        }


        return totalLen;
    }

    public static void printInputStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();

        byte buffer[] = new byte[1024];

        while ( is.read(buffer,0,1024) != -1 ) {
            sb.append(new String(buffer));
        }

        System.out.println(sb.toString());

        safeClose(is);
    }

    public static void logInputStream(InputStream is , Logger log , String level) throws IOException {
        StringBuilder sb = new StringBuilder();

        byte buffer[] = new byte[1024];

        while ( is.read(buffer,0,1024) != -1 ) {
            sb.append(new String(buffer));
        }
        safeClose(is);

        switch (level) {
            case "trace" : {
                log.trace(sb.toString());
                break;
            }
            case "debug" : {
                log.debug(sb.toString());
                break;
            }
            case "warn" : {
                log.warn(sb.toString());
                break;
            }
            case "error" : {
                log.error(sb.toString());
                break;
            }
            default : {
                log.info(sb.toString());
                break;
            }
        }
    }

    public static InputStream resourceInputStream(String resourcePath) {
        Exception e = new Exception();
        StackTraceElement[] stackTrace = e.getStackTrace();
        StackTraceElement invokerElm = stackTrace[1];

        Class invokerClass;

        try {
            invokerClass = Class.forName(invokerElm.getClassName());
        } catch (ClassNotFoundException ignored) {
            invokerClass = StreamUtil.class;
        }

        return invokerClass.getClassLoader().getResourceAsStream(resourcePath);
    }

    public static void safeClose(Closeable closeable) {
        try { closeable.close(); } catch (IOException ignored) {}
    }
}
