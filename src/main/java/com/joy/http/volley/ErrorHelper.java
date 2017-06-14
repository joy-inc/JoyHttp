package com.joy.http.volley;

import android.content.Context;

import com.joy.http.R;

/**
 * Created by KEVIN.DAI on 15/11/7.
 */
public class ErrorHelper {

    // --Volley的异常列表--
    // AuthFailureError：如果在做一个HTTP的身份验证，可能会发生这个错误。
    // NetworkError：Socket关闭，服务器宕机，DNS错误都会产生这个错误。
    // NoConnectionError：和NetworkError类似，这个是客户端没有网络连接。
    // ParseError：在使用JsonObjectRequest或JsonArrayRequest时，如果接收到的JSON是畸形，会产生异常。
    // ServerError：服务器的响应的一个错误，最有可能的4xx或5xx HTTP状态代码。
    // TimeoutError：Socket超时，服务器太忙或网络延迟会产生这个异常。默认情况下，Volley的超时时间为2.5秒。如果得到这个错误可以使用RetryPolicy。

    /**
     * 过滤error的类型，返回相应的提示，如未被命中则返回空串。
     *
     * @param appContext
     * @param error
     * @return Return generic message for errors
     */
    public static String getErrorType(Context appContext, Throwable error) {
        String errorMsg = "";
        if (error == null) {
            return errorMsg;
        }
        if (error instanceof TimeoutError) {
            errorMsg = appContext != null ? appContext.getString(R.string.generic_server_timeout) : "Server Timeout";
        } else if (error instanceof ServerError) {
            errorMsg = appContext != null ? appContext.getString(R.string.generic_server_down) : "Server down";
        } else if (error instanceof AuthFailureError) {
            errorMsg = appContext != null ? appContext.getString(R.string.auth_failed) : "Authentication Failure";
        } else if (error instanceof NetworkError) {
            errorMsg = appContext != null ? appContext.getString(R.string.no_internet) : "No internet";
        } else if (error instanceof RedirectError) {
            errorMsg = appContext != null ? appContext.getString(R.string.redirect_error) : "Redirect Error";
        } else {
//            errorMsg = error.getMessage();
//            errorMsg = "";
        }
        return errorMsg;
    }
}
