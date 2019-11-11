package com.xiongba.ams.vertx;

import com.xiongba.ams.handler.CheckVersionHandler;
import com.xiongba.ams.handler.DecodeHandler;
import com.xiongba.ams.message.AuthCheckVersionRequest;
import com.xiongba.ams.protocol.MessageInputStream;
import com.xiongba.ams.protocol.Protocol;
import com.xiongba.ams.utils.HandlerUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TcpServerVerticle extends AbstractVerticle {
    private static final Log logger = LogFactory.getLog(TcpServerVerticle.class);
    private int port;
    private NetServer server;

    public TcpServerVerticle(int port) {
        if (port > 0) {
            this.port = port;
        }
    }

    @Override
    public void start() throws Exception {
        NetServerOptions options = new NetServerOptions().setPort(port);
        server = vertx.createNetServer(options);
        logger.info("TCP server Start:" + port);
        server.connectHandler(new DecodeHandler());
        server.listen();
    }

    @Override
    public void stop() {
        server.close(new Handler<AsyncResult<Void>>() {
            @Override
            public void handle(AsyncResult result) {
                if (result.succeeded()) {
                    logger.info("TCP server fully closed");
                }
            }
        });
    }
}
//    @Override
//    public void start() throws Exception {
//        NetServerOptions options = new NetServerOptions().setPort(port);
//        NetServer server = vertx.createNetServer(options);
//        server.connectHandler(this::handle);
////        server.close(result -> {
////            if(result.succeeded()){
////                //TCP server fully closed
////                System.out.println("server close succeeded.");
////            }else {
////                System.out.println("server status: " + result.result().toString());
////            }
////        });
//        server.listen();
//    }

    //handle stream
//    private void handle(NetSocket socket) {
//        System.out.println("Incoming connection!");
//        socket.handler(new Handler<Buffer>() {
//
//            @Override
//            public void handle(Buffer buffer) {
//                while (true) {
//                    try {
//                        InputStream inputStream = new ByteArrayInputStream(buffer.getBytes());
//                        MessageInputStream is = new MessageInputStream(inputStream);
//
//                        short ret = -1;
//                        ret = is.read((byte)ret);
//                        if (ret != Protocol.MSG_HEAD_FLAG.getNum())
//                        {
//                            for (int i=0; i<50; i++)
////                                logger.info("head: "+ret+" "+is.read((byte)ret));
//                            is.close();
//                            return;
//                        }
//                        do {
//                            int msgCategory = is.read((byte) ret);
//                            int msgId = is.read(ret);
//                            int msgLen = is.read(ret);
//                            if (msgLen > Protocol.MAX_COMMAND_LENGTH.getNum() || msgLen < 0) {
//                                is.close();
//                                throw new Exception("Socket request is null.");
//                            }
//
//                            Protocol protocolType = Protocol.getProtocolByNum(msgCategory);
//                            switch (protocolType) {
//                                case MSG_CATEGORY_AUTH://客户端
//                                    Protocol protocolNum = Protocol.getProtocolByNum(msgId);
//                                    switch (protocolNum) {
//                                        case MSG_ID_AUTH_CHECK_VERSION_REQUEST://版本验证
//                                            AuthCheckVersionRequest request = new AuthCheckVersionRequest();
//                                            request.decode(is);
//
////                                            logger.info(JsonUtil.getInstance().toJson(request));
//
////                                            HandlerUtil.handle(new CheckVersionHandler(socket, request));
////                                            handler.run(socket, request);
////                                            HandlerUtil.handle();
////                                            CheckVersionHandler((socket, request);
////                                            new CheckVersionHandler(socket, request);
//                                            break;
//                                        case MSG_HEAD_FLAG:
//                                            break;
//                                    }
//                                    break;
//                                case MSG_CATEGORY_LOGIN:
//                                    break;
//                                case MSG_CATEGORY_GM:
//                                    break;
//                            }
//                        } while (ret == Protocol.MSG_HEAD_FLAG.getNum());
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });

//        socket.handler(new DecodeHandler(res -> {
//            if (res.failed()) {
//                // could not parse the message properly
//                System.out.println(res.cause());
//                return;
//            }
//            System.out.println(res.result());
//        }));
//
//        socket.endHandler(v -> {
//        });
//    }

//    public void handle(NetSocket netSocket) {
//        System.out.println("Incoming connection!");
//
//        netSocket.handler(new Handler<Buffer>() {
//
//            @Override
//            public void handle(Buffer inBuffer) {
//                System.out.println("incoming data: " + inBuffer.length());
//
//                String data =
//                        inBuffer.getString(0, inBuffer.length());
//
//                Buffer outBuffer = Buffer.buffer();
//                outBuffer.appendString("response...");
//
//                netSocket.write(outBuffer);
//            }
//        });
//    }
//}