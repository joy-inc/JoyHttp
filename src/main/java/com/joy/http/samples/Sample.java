package com.joy.http.samples;

import com.joy.http.JoyHttp;
import com.joy.http.volley.ObjectRequest;
import com.joy.http.volley.ObjectResponse;
import com.joy.http.volley.ReqFactory;

import java.util.HashMap;
import java.util.Map;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Daisw on 16/9/13.
 */

public class Sample {

    public static void main(String[] args) {

    }

    /**
     * GET请求方式, 并且可以提供完整的URL
     */
    private static void launchNormal1() {

        ObjectRequest<User> objReq = ReqFactory.newGet("www.qyer.com", User.class);
        objReq.setResponseListener(new ObjectResponse<User>() {
            @Override
            public void onSuccess(Object tag, User user) {
            }
        });
        JoyHttp.getLauncher().launchRefreshOnly(objReq);
    }

    /**
     * GET或post请求方式, 提供了基本的URL如"http://open.qyer.com"和参数列表params
     */
    private static void launchNormal2() {

        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("count", "20");

        ObjectRequest<User> objReq = ReqFactory.newGet("http://open.qyer.com", User.class, params);
        objReq.setResponseListener(new ObjectResponse<User>() {
            @Override
            public void onSuccess(Object tag, User user) {
            }
        });
        JoyHttp.getLauncher().launchRefreshOnly(objReq);
    }

    /**
     * GET或post请求方式, 提供了基本的URL如"http://open.qyer.com"、参数列表params和请求头headers
     */
    private static void launchNormal3() {

        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("count", "20");

        Map<String, String> headers = new HashMap<>();
        headers.put("api-auth", "AD7A1775CA0B3154F9EDD");
        headers.put("user-token", "user_token");

        ObjectRequest<User> objReq = ReqFactory.newGet("http://open.qyer.com", User.class, params, headers);
        objReq.setResponseListener(new ObjectResponse<User>() {
            @Override
            public void onSuccess(Object tag, User user) {
            }
        });
        JoyHttp.getLauncher().launchRefreshOnly(objReq);
    }

    private static void launchNormal4() {

        ObjectRequest<User> objReq = ReqFactory.newGet("api", User.class);
        objReq.setResponseListener(new ObjectResponse<User>() {
            @Override
            public void onSuccess(Object tag, User user) {
            }

            @Override
            public void onError(Object tag, String msg) {
            }
        });
        JoyHttp.getLauncher().launchRefreshOnly(objReq);
    }

    private static void launchRx1() {

        ObjectRequest<User> objReq = ReqFactory.newGet("api", User.class);
        JoyHttp.getLauncher().launchRefreshOnly(objReq)
                .filter(new Func1<User, Boolean>() {
                    @Override
                    public Boolean call(User user) {
                        return user != null;
                    }
                })
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                    }
                });
    }

    private static void launchRx2() {

        ObjectRequest<User> objReq = ReqFactory.newGet("api", User.class);
        JoyHttp.getLauncher().launchRefreshOnly(objReq)
                .filter(new Func1<User, Boolean>() {
                    @Override
                    public Boolean call(User user) {
                        return user != null;
                    }
                })
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
    }

    private static void launchRx3() {

        ObjectRequest<User> objReq = ReqFactory.newGet("api", User.class);
        JoyHttp.getLauncher().launchRefreshOnly(objReq)
                .filter(new Func1<User, Boolean>() {
                    @Override
                    public Boolean call(User user) {
                        return user != null;
                    }
                })
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                    }
                });
    }
}