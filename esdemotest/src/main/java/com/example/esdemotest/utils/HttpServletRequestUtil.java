package com.example.esdemotest.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.esdemotest.conext.RedisConstant;
import com.example.esdemotest.result.ResponseCode;
import com.example.esdemotest.result.ResultWrap;
import io.netty.util.internal.StringUtil;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhongxianyin
 * @ClassName : com.cifmedia.utils.HttpServletRequestUtil
 * @E-mail : music120326@hotmail.com
 * @date : 2019/4/11 9:16
 */
public class HttpServletRequestUtil {

    public final static String VERSION = "version";
    public final static String PLATFORM = "platform";
    public final static String MODEL = "model";
    public final static String IMEI = "imei";
    public final static String SIGN = "sign";


    public static ResultWrap<String> getRedisKeyByPlatform(HttpServletRequest request) {
        Map<String, String> queryParam = HttpServletRequestUtil.getParam(request);
        String platform = queryParam.get(HttpServletRequestUtil.PLATFORM);
        if (StringUtil.isNullOrEmpty(platform)) {
            return ResultWrap.failure(ResponseCode.CODE_2209, null, "缺少平台ID");
        }
        HashMap<String, String> platformRedisKey = new HashMap<>(6);
        platformRedisKey.put("IOS", RedisConstant.APP_LOGIN_TOKEN);
        platformRedisKey.put("ANDROID", RedisConstant.APP_LOGIN_TOKEN);
        platformRedisKey.put("H5", RedisConstant.H5_LOGIN_TOKEN);
        platformRedisKey.put("WE_CHAT", RedisConstant.WE_CHAT_LOGIN_TOKEN);
        platformRedisKey.put("WEB", RedisConstant.WEB_LOGIN_TOKEN);
        platformRedisKey.put("SYS", RedisConstant.SYS_LOGIN_TOKEN);
        String redisKey = platformRedisKey.get(platform.toUpperCase());
        if (StringUtil.isNullOrEmpty(redisKey)) {
            return ResultWrap.failure(ResponseCode.CODE_2209, null, "平台ID 错误");
        }
        return ResultWrap.success(redisKey);
    }

    /**
     * 获取header 和query参数
     *
     * @param request 请求头
     * @return
     */
    public static Map<String, String> getParam(HttpServletRequest request) {
        HashMap<String, String> param = new HashMap<>(30);
        param.putAll(getHeaderParam(request));
        Map<String, String> queryParam = getQueryParam(request);
        if (queryParam != null && !queryParam.isEmpty()) {
            param.putAll(queryParam);
        }
        Map<String, String> resultMap = new HashMap<>(30);
        param.entrySet().forEach(x -> resultMap.put(x.getKey().toLowerCase(), x.getValue()));
        return resultMap;
    }

    /**
     * 从HttpServletRequest获取header参数
     *
     * @param request 请求头
     * @return
     */
    public static Map<String, String> getHeaderParam(HttpServletRequest request) {
        HashMap<String, String> headerParam = new HashMap<>(15);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            headerParam.put(element, request.getHeader(element));
        }
        return headerParam;
    }


    /**
     * 从HttpServletRequest获取query参数
     *
     * @param request 请求头
     * @return
     */
    public static Map<String, String> getQueryParam(HttpServletRequest request) {
        String queryString = request.getQueryString();

        if (StringUtils.hasText(queryString)) {
            String[] split = queryString.split("&");
            Map<String, String> result = new HashMap<>(split.length);
            for (String s : split) {
                String[] queryParam = s.split("=");
                result.put(queryParam[0], queryParam[1]);
            }
            return result;
        }
        return null;
    }

    /**
     * 从HttpServletRequest获取body的请求数据
     *
     * @param request 请求头
     * @return
     */
    public static Map<String, String> getBodyParam(HttpServletRequest request) {
        String result = getBody(request);
        if (!StringUtil.isNullOrEmpty(result)) {
            Map<String, Object> parse = (Map<String, Object>) JSONObject.parse(result);
            Map<String, String> resultMap = new HashMap<>();
            parse.entrySet().forEach(x -> resultMap.put(x.getKey(), x.getValue().toString()));
            return resultMap;
        }
        return null;
    }

    public static String getBody(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
