package com.joy.http;

import com.joy.http.utils.ParamsUtil;
import com.joy.http.volley.ObjectRequest;

import java.util.Map;

/**
 * Created by Daisw on 16/9/14.
 */

public class ReqFactory {

    /**
     * @param baseUrl
     * @param clazz
     * @param params  length = [0,2]
     * @param <T>
     * @return
     */
    public static <T> ObjectRequest<T> newGet(String baseUrl, Class<?> clazz, Map<String, String>... params) {
        checkParamsIsValid(params);
        StringBuilder sb = new StringBuilder(baseUrl);
        if (isParamsSingle(params)) {
            sb.append('?').append(ParamsUtil.createUrl(params[0]));
        } else if (isParamsDouble(params)) {
            String fullUrl = baseUrl;
            final Map<String, String> p = params[0];
            if (p != null && !p.isEmpty()) {
                fullUrl = sb.append('?').append(ParamsUtil.createUrl(p)).toString();
            }
            ObjectRequest<T> req = ObjectRequest.get(fullUrl, clazz);
            req.setHeaders(params[1]);
            return req;
        }
        return ObjectRequest.get(sb.toString(), clazz);
    }

    /**
     * @param baseUrl
     * @param clazz
     * @param params  length = [0,2]
     * @param <T>
     * @return
     */
    public static <T> ObjectRequest<T> newPost(String baseUrl, Class<?> clazz, Map<String, String>... params) {
        checkParamsIsValid(params);
        ObjectRequest<T> req = ObjectRequest.post(baseUrl, clazz);
        if (isParamsSingle(params)) {
            req.setParams(params[0]);
        } else if (isParamsDouble(params)) {
            req.setParams(params[0]);
            req.setHeaders(params[1]);
        }
        return req;
    }

    public static void checkParamsIsValid(Map<String, String>... params) {
        if (params != null && params.length > 2) {
            throw new IllegalArgumentException("可变参数params的length不能大于2");
        }
    }

    public static boolean isParamsSingle(Map<String, String>... params) {
        return params != null && params.length == 1;
    }

    public static boolean isParamsDouble(Map<String, String>... params) {
        return params != null && params.length == 2;
    }
}
