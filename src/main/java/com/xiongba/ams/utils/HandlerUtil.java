package com.xiongba.ams.utils;

import com.xiongba.ams.handler.CheckVersionHandler;
import com.xiongba.ams.vertx.VertxUtil;

public class HandlerUtil {
    public static void handle(Runnable handler){
        new Thread(handler).start();
    }


//    public static <T> T getAsyncServiceInstance(Class<T> asClazz) {
//        String address = asClazz.getName();
//        return new ServiceProxyBuilder(VertxUtil.getVertxInstance()).setAddress(address).build(asClazz);
//    }
}