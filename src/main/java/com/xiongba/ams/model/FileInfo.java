package com.xiongba.ams.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileInfo implements Serializable {
    private String file;
    private String fileTime;
    private int fileSize;
}
