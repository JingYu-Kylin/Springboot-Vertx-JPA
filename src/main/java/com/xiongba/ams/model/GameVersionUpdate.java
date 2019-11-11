package com.xiongba.ams.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameVersionUpdate implements Serializable {
    private int id;
    private int channle;
    private int fromGv;
    private int fromDv;
    private int toGv;
    private int toDv;
    private String updateUrl;
}
