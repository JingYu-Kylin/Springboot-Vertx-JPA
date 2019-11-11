package com.xiongba.ams.vertx;

import com.xiongba.ams.controller.GmCommandController;
import com.xiongba.ams.controller.PayCallbackController;
import com.xiongba.ams.controller.PlatformController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

public class HttpServerVerticle extends AbstractVerticle {

    private static final int MAX_WEBSOCKET_FRAME_SIZE = 1000000;
    private int port;
    private HttpServer server;

    public HttpServerVerticle(int port) {
        if (port > 0) {
            this.port = port;
        }
    }

    @Override
    public void start() throws Exception {
        super.start();
        HttpServerOptions options = new HttpServerOptions().setMaxWebsocketFrameSize(MAX_WEBSOCKET_FRAME_SIZE).setPort(port);
        server = vertx.createHttpServer(options);

        //web路由器
        Router router = Router.router(vertx);
        router.route(HttpMethod.POST,"/gm/*").handler(GmCommandController::gmRouter);
        router.route(HttpMethod.POST,"/api/*").handler(PlatformController::apiRouter);
        router.route("/pay/*").handler(PayCallbackController::payRouter);

        server.requestHandler(router);
        server.listen(result -> {
            if (result.succeeded()) {
            } else {
            }
        });
    }

    @Override
    public void stop() {
        if (server == null) {
            return;
        }
        server.close(result -> {
            if (result.failed()) {
            } else {
            }
        });
    }
}