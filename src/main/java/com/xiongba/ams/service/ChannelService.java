package com.xiongba.ams.service;

import com.xiongba.ams.entity.Channel;

public interface ChannelService {
    Channel getChannelById (int id);
    Channel getChannelByCid (int cid);

}
