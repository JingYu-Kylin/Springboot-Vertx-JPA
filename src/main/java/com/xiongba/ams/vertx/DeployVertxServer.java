package com.xiongba.ams.vertx;

import java.io.IOException;

/**
 * 开始注册vertx相关服务
 */
public class DeployVertxServer {
    public static void startDeploy(int tcpPort, int httpPort) throws IOException {
        VertxUtil.getVertxInstance().deployVerticle(new HttpServerVerticle(httpPort));
        VertxUtil.getVertxInstance().deployVerticle(new TcpServerVerticle(tcpPort));

//        if (asyncServiceInstances < 1) {
//            asyncServiceInstances = 1;
//        }
//        for (int i = 0; i < asyncServiceInstances; i++) {
//            VertxUtil.getVertxInstance().deployVerticle(new TcpServerVerticle(asyncServiceImplPackages), new DeploymentOptions().setWorker(true));
//        }
    }
}
