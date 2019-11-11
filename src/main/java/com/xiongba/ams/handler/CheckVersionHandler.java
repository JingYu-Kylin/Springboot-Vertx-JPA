package com.xiongba.ams.handler;

import com.xiongba.ams.entity.Channel;
import com.xiongba.ams.message.AuthCheckVersionRequest;
import com.xiongba.ams.message.AuthCheckVersionResponse;
import com.xiongba.ams.service.ChannelService;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

public class CheckVersionHandler implements Runnable {
    private static final Log logger = LogFactory.getLog(CheckVersionHandler.class);
    private NetSocket socket;
    private AuthCheckVersionRequest request;

    private ChannelService channelService;

//    @Autowired
//    public CheckVersionHandler(@Qualifier("channelService") ChannelService channelService) {
//        this.channelService = channelService;
//    }
    @PostConstruct
    public void init () {

    }
    @Override
    public void run() {
        AuthCheckVersionResponse response = new AuthCheckVersionResponse();
//        ChannelService channelService = SpringUtils.getBean("channelService");
        Channel channel = channelService.getChannelByCid(request.getChannelId());

        System.out.println(channel);


        Buffer outBuffer = Buffer.buffer();

        socket.write(outBuffer);
    }


}
