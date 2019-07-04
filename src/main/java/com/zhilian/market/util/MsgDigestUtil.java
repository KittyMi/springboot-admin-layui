package com.zhilian.market.util;


import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Link on 2016/4/19.
 * 信息摘要
 */
public class MsgDigestUtil {

    /**
     * 32位MD5
     * @param msg 要摘要的字节数组
     * @return MD5 Str
     */
    public static DigestAndLength getMD5(byte[] msg) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg);
            result = digestToString(md);
//            System.out.println("MD5(" + entity + ",32) = " + result);
//            System.out.println("MD5(" + entity + ",16) = " + buf.toString().substring(8, 24));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new DigestAndLength().setDigest(result).setLength((long) msg.length);
    }

    /**
     * 32位MD5
     * @param msg 要摘要的字符串
     * @return MD5 Str
     */
    public static DigestAndLength getMD5(String msg) {
        return getMD5(msg.getBytes());
    }

    public static DigestAndLength getMD5(InputStream is) throws IOException {
        DigestAndLength dal = new DigestAndLength();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            int bufferSize = 1024;
            byte buffer[] = new byte[1024];
            long totalLen = 0;
            int readLen;

            while ( (readLen = is.read(buffer,0,bufferSize)) > 0 ) {
                md.update(buffer,0,readLen);

                totalLen += readLen;
            }
            dal.setDigest(digestToString(md)).setLength(totalLen);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return dal;
    }

    /**
     * 阿里的MD5（巨坑！和想象中的不太一样！）
     * @param file
     * @return
     */
    public static DigestAndLength getAliMD5(File file) {
        DigestAndLength dal = new DigestAndLength();
        try(FileInputStream fis = new FileInputStream(file)) {
            MessageDigest md = MessageDigest.getInstance("MD5");

            long len = 0;
            byte buffer[] = new byte[1024];
            int read;

            while ( (read = fis.read(buffer,0,1024)) != -1) {
                md.update(buffer, 0, read);
                len += read;
            }

            dal.setDigest(new String(Base64.encodeBase64(md.digest()))).setLength(len);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dal;
    }

    public static String aliMD5ToNormal(String aliMD5) {
        return toHexString(Base64.decodeBase64(aliMD5));
    }

    private static String digestToString(MessageDigest md) {
        return toHexString(md.digest());
    }

    private static String toHexString(byte digest[]) {
        int i;
        StringBuilder buf = new StringBuilder("");
        for (byte aB : digest) {
            i = aB;
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    /**
     * 40位SHA1
     * @param msg 要摘要的字符串
     * @return SHA1
     */
    public static String getSHA1(String msg) {
        return getSHA1(msg.getBytes());
    }

    /**
     * 40位SHA1
     * @param msg 要摘要的字节数组
     * @return SHA1
     */
    public static String getSHA1(byte[] msg) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(msg);
            return digestToString(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static class DigestAndLength implements Serializable {
        private static final long serialVersionUID = -8256957190127979916L;

        private String digest;
        private Long length;

        public String getDigest() {
            return digest;
        }

        public DigestAndLength setDigest(String digest) {
            this.digest = digest;
            return this;
        }

        public Long getLength() {
            return length;
        }

        public DigestAndLength setLength(Long length) {
            this.length = length;
            return this;
        }
    }
}
