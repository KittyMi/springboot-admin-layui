package com.zhilian.market.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Link on 2017/4/18.
 */
public class ParamUtil {
    private static Logger log = LoggerFactory.getLogger(ParamUtil.class);

    private Map<String,String> map;
    private String remoteIP;
    private String uri;
    private Long requestTimestamp;
    private Boolean requiredFieldsSatisfy;
    private List<String> missingFields;

    @SuppressWarnings("unchecked")
    public ParamUtil(HttpServletRequest request) {
        Map<String,String[]> paramMap = request.getParameterMap();

        map = new HashMap<>(paramMap.size() + 4);

        for(String key : paramMap.keySet()) {
            map.put(key, StringUtils.join(paramMap.get(key),"|"));
        }
        this.remoteIP = request.getRemoteAddr();
        this.uri = request.getRequestURI();
        this.requestTimestamp = System.currentTimeMillis();

        log.debug(toString());

        this.requiredFieldsSatisfy = true;
        this.missingFields = new ArrayList<>(map.size());
    }

    public ParamUtil(HttpServletRequest request, String ...requireFields) {
        this(request);

        for(String field : requireFields) {
            if(!map.containsKey(field)) {
                this.requiredFieldsSatisfy = false;
                missingFields.add(field);
            }
        }
    }

    public String get(String key) {
        return map.get(key);
    }

    public String getStr(String key) {
        return get(key);
    }

    public String getStr(String key,String defaultValue) {
        return getStr(key) == null ? defaultValue : getStr(key);
    }

    public Integer getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public Integer getInt(String key,Integer defaultValue) {
        return getInt(key) == null ? defaultValue : getInt(key);
    }

    public Float getFloat(String key) {
        return Float.parseFloat(get(key));
    }

    public Float getFloat(String key, Float defaultValue) {
        return getFloat(key) == null ? defaultValue : getFloat(key);
    }

    public Double getDouble(String key) {
        return Double.parseDouble(get(key));
    }

    public Double getDouble(String key,Double defaultValue) {
        return getDouble(key) == null ? defaultValue : getDouble(key);
    }

    public Boolean getBool(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public Boolean getBool(String key,Boolean defaultValue) {
        return getBool(key) == null ? defaultValue : getBool(key);
    }

    public String getBase64DecodeStr(String key) {
        return getBase64DecodeStr(key, Charset.forName("utf-8"));
    }

    public String getBase64DecodeStr(String key,Charset charset) {
        return new String(getBase64Decode(key),charset);
    }

    public byte[] getBase64Decode(String key) {
        return Base64.decodeBase64(get(key));
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(map);
    }

    public String[] getArray(String key) {
        return get(key).split("\\|");
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public String getUri() {
        return uri;
    }

    public Long getRequestTimestamp() {
        return requestTimestamp;
    }

    public Boolean isRequiredFieldsSatisfy() {
        return requiredFieldsSatisfy;
    }

    public List<String> getMissingFields() {
        return missingFields;
    }
}
