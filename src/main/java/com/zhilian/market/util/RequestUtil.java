package com.zhilian.market.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * request工具类
 * Created by ZhangShuzheng on 2016/12/12.
 */
public class RequestUtil {

	/**
	 * 移除request指定参数
	 * @param request
	 * @param paramName
	 * @return
	 */
	public String removeParam(HttpServletRequest request, String paramName) {
		String queryString = "";
		Enumeration keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			if (key.equals(paramName)) {
				continue;
			}
			if (queryString.equals("")) {
				queryString = key + "=" + request.getParameter(key);
			} else {
				queryString += "&" + key + "=" + request.getParameter(key);
			}
		}
		return queryString;
	}

	/**
	 * 获取请求basePath
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		StringBuffer basePath = new StringBuffer();
		String scheme = request.getScheme();
		String domain = request.getServerName();
		int port = request.getServerPort();
		basePath.append(scheme);
		basePath.append("://");
		basePath.append(domain);
		if("http".equalsIgnoreCase(scheme) && 80 != port) {
			basePath.append(":").append(String.valueOf(port));
		} else if("https".equalsIgnoreCase(scheme) && port != 443) {
			basePath.append(":").append(String.valueOf(port));
		}
		return basePath.toString();
	}

	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { //真实ip
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }

	/**
	 * 判断Url是json
	 * @param url
	 * @return
	 */
	public static boolean isJsonUrl(String url) {
		return RxFileUtils.getFileExtension(url).equals(".json");
	}

	/**
	 * 判断是json请求
	 * @param request
	 * @return
	 */
	public static boolean isJsonRequest(HttpServletRequest request) {
		return isJsonUrl(request.getRequestURL().toString());
	}

	/**
	 * 判断Url是html
	 * @param url
	 * @return
	 */
	public static boolean isHtmlUrl(String url) {
		String fileExtension = RxFileUtils.getFileExtension(url);
		return StringUtils.isNoneEmpty(fileExtension) && fileExtension.equals(".html");
	}

	/**
	 * 判断是html请求
	 * @param request
	 * @return
	 */
	public static boolean isHtmlRequest(HttpServletRequest request) {
		return isHtmlUrl(request.getRequestURL().toString());
	}

	/**
	 * 判断是否为ajax请求
	 * @param request
	 * @return
	 */
	public static String getRequestType(HttpServletRequest request) {
		return request.getHeader("X-Requested-With");
	}
}
