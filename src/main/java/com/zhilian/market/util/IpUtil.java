package com.zhilian.market.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Link on 2017/10/9.
 */
public class IpUtil {
    public static String getLocalIp() {
        String hostAddress = "";

        try {
            InetAddress address = InetAddress.getLocalHost();
            hostAddress = address.getHostAddress();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostAddress;
    }
}
