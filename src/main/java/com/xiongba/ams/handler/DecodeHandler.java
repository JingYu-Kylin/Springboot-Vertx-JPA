package com.xiongba.ams.handler;

import com.xiongba.ams.entity.Channel;
import com.xiongba.ams.message.AuthCheckVersionRequest;
import com.xiongba.ams.protocol.MessageInputStream;
import com.xiongba.ams.protocol.Protocol;
import com.xiongba.ams.service.ChannelService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DecodeHandler implements Handler<NetSocket> {
    public static Logger logger = LoggerFactory.getLogger(DecodeHandler.class);
    @Autowired
    private ChannelService channelService;

//    @Autowired
//    public DecodeHandler(@Qualifier("channelService") ChannelService channelService) {
//        this.channelService = channelService;
//    }

    @Override
    public void handle(NetSocket socket) {
        socket.handler(new Handler<Buffer>() {
            @Override
            public void handle(Buffer inBuffer) {
                while (true) {
                    InputStream inputStream = new ByteArrayInputStream(inBuffer.getBytes());
                    MessageInputStream is = new MessageInputStream(inputStream);

                    try {
                        short ret = is.read((byte)-1);
                        while (ret != Protocol.MSG_HEAD_FLAG.getNum()) {
                            for (int i=0; i<50; i++)
                                logger.info("head: "+ret+" "+is.read((byte)ret));
                            is.close();
                            return;
                        }

                        while (ret == Protocol.MSG_HEAD_FLAG.getNum()) {
                            int msgCategory = is.read((byte) ret);
                            int msgId = is.read(ret);
                            int msgLen = is.read(ret);
                            if (msgLen > Protocol.MAX_COMMAND_LENGTH.getNum() || msgLen < 0) {
                                logger.info("Socket request is null:" + is);
                                is.close();
                            }

                            Protocol protocolType = Protocol.getProtocolByNum(msgCategory);
                            switch (protocolType) {
                                case MSG_CATEGORY_AUTH://客户端
                                    Protocol protocolNum = Protocol.getProtocolByNum(msgId);
                                    switch (protocolNum) {
                                        case MSG_ID_AUTH_CHECK_VERSION_REQUEST://版本验证
                                            AuthCheckVersionRequest request = new AuthCheckVersionRequest();
                                            request.decode(is);
                                            Buffer outBuffer = Buffer.buffer();


                                            socket.write(outBuffer);
                                            break;
                                        case MSG_HEAD_FLAG:
                                            break;
                                    }
                                    break;
                                case MSG_CATEGORY_LOGIN:
                                    break;
                                case MSG_CATEGORY_GM:
                                    break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Buffer outBuffer = Buffer.buffer();
                    outBuffer.appendString("response...");

                    socket.write(outBuffer);
                }
            }
        });
    }
}
