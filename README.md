### JoyHttp

Android网络请求库

### 请求方式：  
- `REFRESH_ONLY` 获取网络，响应；
- `CACHE_ONLY` 获取缓存，响应；
- `REFRESH_AND_CACHE` 缓存过期了，获取网络，更新缓存，响应；
- `CACHE_AND_REFRESH` 获取缓存，响应，获取网络，更新缓存，响应；

**注意：每种请求方式的响应次数！**

### 回调方式：
- `RX订阅`
- `Listener callback`

### 第三方支持：
- [x] `Volley`
- [ ] `Retrofit` `Okhttp`

### 用法示例

##### 初始化

```
public class JoyApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        JoyHttp.initialize(this, BuildConfig.DEBUG);
    }
}
```

##### 发送请求

- 普通回调方式

GET请求, 并且提供了完整的URL。代码如下:

```
ObjectRequest<User> objReq = ReqFactory.newGet("www.qyer.com", User.class);
objReq.setResponseListener(new ObjectResponse<User>() {
    @Override
    public void onSuccess(Object tag, User user) {
    }

    @Override
    public void onError(Object tag, String msg) {
    }
});
JoyHttp.getLauncher().launchRefreshOnly(objReq);
```

倘若我们只关心成功的回调, 可以不重载onError方法。代码如下:

```
ObjectRequest<User> objReq = ReqFactory.newGet("www.qyer.com", User.class);
objReq.setResponseListener(new ObjectResponse<User>() {
    @Override
    public void onSuccess(Object tag, User user) {
    }
});
JoyHttp.getLauncher().launchRefreshOnly(objReq);
```

GET请求, 提供了基本的URL和参数列表。代码如下:

```
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
```

GET请求, 提供了基本的URL、参数列表和请求头。代码如下:

```
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
```

- Rx订阅方式

关心成功、失败、完成

```
ObjectRequest<User> objReq = ReqFactory.newGet("www.qyer.com", User.class);
JoyHttp.getLauncher().launchRefreshOnly(objReq)
        .filter(new Func1<User, Boolean>() {
            @Override
            public Boolean call(User user) {
                return user != null;
            }
        })
        .subscribe(new Action1<User>() {
            @Override
            public void call(User user) {// success
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {// error
            }
        }, new Action0() {
            @Override
            public void call() {// complete
            }
        });
```

只关心成功

```
ObjectRequest<User> objReq = ReqFactory.newGet("www.qyer.com", User.class);
JoyHttp.getLauncher().launchRefreshOnly(objReq)
        .filter(new Func1<User, Boolean>() {
            @Override
            public Boolean call(User user) {
                return user != null;
            }
        })
        .subscribe(new Action1<User>() {
            @Override
            public void call(User user) {// success
            }
        });
```

只关心成功和失败

```
ObjectRequest<User> objReq = ReqFactory.newGet("www.qyer.com", User.class);
JoyHttp.getLauncher().launchRefreshOnly(objReq)
        .filter(new Func1<User, Boolean>() {
            @Override
            public Boolean call(User user) {
                return user != null;
            }
        })
        .subscribe(new Action1<User>() {
            @Override
            public void call(User user) {// success
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {// error
            }
        });
```

##### 销毁

```
JoyHttp.shutDown();
```