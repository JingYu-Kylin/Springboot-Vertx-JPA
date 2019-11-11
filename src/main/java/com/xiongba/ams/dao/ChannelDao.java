package com.xiongba.ams.dao;

import com.xiongba.ams.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChannelDao extends JpaRepository<Channel, Integer>, JpaSpecificationExecutor<Channel> {
}
