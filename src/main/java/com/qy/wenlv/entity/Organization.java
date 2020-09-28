package com.qy.wenlv.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Barret
 * @description
 * @date 8/7/2020
 */
@Data
public class Organization implements Serializable {

    private static final long serialVersionUID = -8478114427891717226L;

    private Integer id;
    //英文名称admin
    private String name;
}
