package com.xiongba.ams.message;

import com.xiongba.ams.protocol.MessageInputStream;
import com.xiongba.ams.protocol.MessageOutputStream;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BaseMessage implements Serializable {
    private int msgId;
    private int msgCategory;

    public boolean encode(MessageOutputStream os) {
        return true;
    }

    public boolean decode(MessageInputStream is) throws Exception {
        return true;
    }
}
