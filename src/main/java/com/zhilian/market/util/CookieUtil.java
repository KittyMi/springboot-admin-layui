package com.zhilian.market.util;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    /**
     * 创建cookie
     * @param httpServletResponse
     * @param name
     * @param value
     * @param secure
     * @param maxAge
     * @param domain
     */
    public static void create(HttpServletResponse httpServletResponse, String name, String value, Boolean secure, Integer maxAge, String domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(secure);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    /**
     * 清除cookie
     * @param httpServletResponse
     * @param name
     */
    public static void clear(HttpServletResponse httpServletResponse, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setDomain("localhost");
        httpServletResponse.addCookie(cookie);
    }

    /**
     * 获取cookieValue
     * @param httpServletRequest
     * @param name
     * @return
     */
    public static String getValue(HttpServletRequest httpServletRequest, String name) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
        return cookie != null ? cookie.getValue() : null;
    }
}

