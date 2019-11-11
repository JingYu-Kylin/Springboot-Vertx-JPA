package com.xiongba.ams.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameVersion implements Serializable {

    private int id;
    private Integer channelId; // 渠道
    private int gameVersion;
    private int dataVersion;
    private int svn;
    private int isForceUpdate;
}
