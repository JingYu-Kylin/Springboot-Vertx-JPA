package com.xiongba.ams.service.impl;

import com.xiongba.ams.dao.ChannelDao;
import com.xiongba.ams.entity.Channel;
import com.xiongba.ams.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("channelService")
@Transactional
public class ChannelServiceImpl implements ChannelService {
    private ChannelDao channelDao;
    private EntityManager entityManager;

    @Autowired
    public ChannelServiceImpl(ChannelDao channelDao, EntityManager entityManager) {
        this.channelDao = channelDao;
        this.entityManager = entityManager;
    }
    @Override
    public Channel getChannelById(int id) {
        // 创建查询条件数据对象
        Channel channel = new Channel();
        channel.setId(id);
//        // 创建匹配器，即规定如何使用查询条件
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // 改变默认字符串匹配方式：模糊查询，即%{name}%，(默认为精确查询)
//                .withIgnoreCase(true) // 改变默认大小写忽略方式：忽略大小写
//                // withMatcher可以改变特定字段的查询方式
//                //.withMatcher("name", ExampleMatcher.GenericPropertyMatcher::startsWith) // 模糊查询匹配开头，即{name}%
//                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase().contains()) // 忽略大小写，完全模糊查询，即where (lower(name) like %{name}%)
//                .withIgnorePaths("status") // 忽略属性列表，忽略的属性不参与查询过滤
//                .withNullHandler(ExampleMatcher.NullHandler.IGNORE) // 当属性为null时是否参与过滤(IGNORE,INCLUDE),默认为IGNORE（忽略）
//                ;
        // 创建实例
        Example<Channel> example = Example.of(channel);
        // 查询
        Optional<Channel> result = channelDao.findOne(example);
        if(result!=null)
            return result.get();
        return null;
    }

    @Override
    public Channel getChannelByCid(int cid) {
        // 创建查询条件数据对象
        Channel channel = new Channel();
        channel.setCid(cid);
        // 创建实例
        Example<Channel> example = Example.of(channel);
        // 查询
        Optional<Channel> result = channelDao.findOne(example);
        if(result!=null)
            return result.get();
        return null;
    }
}
