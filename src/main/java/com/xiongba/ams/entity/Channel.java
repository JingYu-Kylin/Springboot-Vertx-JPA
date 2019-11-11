package com.xiongba.ams.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "gms_channel") //对应数据库中的表名
public class Channel implements Serializable {
    private static final long serialVersionUID = -5103936306962248929L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cid;
    private String name;
    private String alias;
    private int status;
    private int idx;
    private String announcement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}