package com.zhilian.market.util;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by Link on 2016/9/13.
 * 字节数组工具
 */
public class ByteArrayUtil {

    public static byte[] toByte(boolean data) {
        return ByteBuffer.allocate(1).put((byte) ((data)?1:0)).array();
    }

    public static byte[] toByte(Boolean data) {
        return toByte(data.booleanValue());
    }

    /**
     * 转字节数组
     * @param data char value
     * @return 字节数组
     */
    public static byte[] toByte(char data) {
        return ByteBuffer.allocate(2).putChar(0,data).array();
    }

    public static byte[] toByte(Character data) {
        return toByte(data.charValue());
    }
    /**
     * 转字节数组
     * @param data short value
     * @return 字节数组
     */
    public static byte[] toByte(short data) {
        return ByteBuffer.allocate(2).putShort(0,data).array();
    }

    public static byte[] toByte(Short data) {
        return toByte(data.shortValue());
    }
    /**
     * 转字节数组
     * @param data int value
     * @return 字节数组
     */
    public static byte[] toByte(int data) {
        return ByteBuffer.allocate(4).putInt(0,data).array();
    }

    public static byte[] toByte(Integer data) {
        return toByte(data.intValue());
    }

    /**
     * 转字节数组
     * @param data float value
     * @return 字节数组
     */
    public static byte[] toByte(float data) {
        return ByteBuffer.allocate(4).putFloat(0,data).array();
    }

    public static byte[] toByte(Float data) {
        return toByte(data.floatValue());
    }

    /**
     * 转字节数组
     * @param data long value
     * @return 字节数组
     */
    public static byte[] toByte(long data) {
        return ByteBuffer.allocate(8).putLong(0,data).array();
    }

    public static byte[] toByte(Long data) {
        return toByte(data.longValue());
    }

    /**
     * 转字节数组
     * @param data double value
     * @return 字节数组
     */
    public static byte[] toByte(double data) {
        return ByteBuffer.allocate(8).putDouble(0,data).array();
    }

    public static byte[] toByte(Double data) {
        return toByte(data.doubleValue());
    }

    public static byte[] toByte(String s) {
        return s.getBytes();
    }

    /**
     * 将可序列化的对象进行序列化
     * @param data 可序列化对象
     * @param <T> 序列化对象类型
     * @return 序列化后的字节数组
     */
    public static <T extends Serializable> byte[] toByte(T data) {

        byte ret[] = null;
        try (ByteArrayOutputStream bao = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bao)) {

            oos.writeObject(data);
            bao.flush();

            ret = bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static boolean toBoolean(byte data[]) {
        ByteBuffer buffer = ByteBuffer.allocate(1);
        buffer.put(data,0,data.length > 1 ? 1 : data.length);
        buffer.flip();
        return buffer.get(0) == (byte)1;
    }

    /**
     * 字节数组转char
     * @param data 字节数组
     * @return char value
     */
    public static char toChar(byte data[]) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.put(data,0,data.length > 2 ? 2 : data.length);
        buffer.flip();
        return buffer.getChar();
    }

    /**
     * 字节数组转int
     * @param data 字节数组
     * @return int value
     */
    public static int toInt(byte data[]) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put(data,0,data.length > 4 ? 4 : data.length);
        buffer.flip();
        return buffer.getInt();
    }

    /**
     * 字节数组转short
     * @param data 字节数组
     * @return short value
     */
    public static short toShort(byte data[]) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.put(data,0,data.length > 2 ? 2 : data.length);
        buffer.flip();
        return buffer.getShort();
    }

    /**
     * 字节数组转float
     * @param data 字节数组
     * @return float value
     */
    public static float toFloat(byte data[]) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put(data,0,data.length > 4 ? 4 : data.length);
        buffer.flip();
        return buffer.getFloat();
    }

    /**
     * 字节数组转long
     * @param data 字节数组
     * @return long value
     */
    public static long toLong(byte data[]) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(data,0,data.length > 8 ? 8 : data.length);
        buffer.flip();
        return buffer.getLong();
    }

    /**
     * 字节数组转double
     * @param data 字节数组
     * @return double value
     */
    public static double toDouble(byte data[]) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(data,0,data.length > 8 ? 8 : data.length);
        buffer.flip();
        return buffer.getDouble();
    }

    public static String toStr(byte data[]) {
        return toStr(data,"utf-8");
    }

    public static String toStr(byte data[],String charset) {
        try {
            return new String(data,charset);
        } catch (UnsupportedEncodingException ignored) {
        }
        return null;
    }

    /**
     * 反序列化字节数组
     * @param data 序列化后的数据
     * @param <T> 要反序列化的对象类型
     * @return 反序列化后的对象
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T toObject(byte data[]) {
        T ret = null;
        try( ByteArrayInputStream bis = new ByteArrayInputStream(data) ;
             ObjectInputStream ois = new ObjectInputStream(bis) ) {

            ret =  (T)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 字节数组打印到stdout
     * @param data 字节数组
     */
    public static void stdout(byte data[]) {
        if(data!= null && data.length>0) {
            for (byte b : data) {
                System.out.print(b + " ");
            }
        }
        System.out.println();
    }
}
