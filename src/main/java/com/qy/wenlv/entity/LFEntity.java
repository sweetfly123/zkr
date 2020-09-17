package com.qy.wenlv.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Barret
 * @description
 * @date 8/14/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lv_service")
public class LFEntity {
    private Integer id;
    private String name;
    private String userId;
    private String serveType;
    private String resourceType;
    private String productType;
    private String summary;
    private String postHits;
    private String thumbnail;
    private String province;
    private String cities;
    private String corpId;
    private String status;
    private String recommend;
    private String coCount;
    @TableField("cname")
    private String cName;
    private String logo;
    @TableField("uname")
    private String uName;
    @TableField("cstatus")
    private String cStatus;
    @TableField("istatus")
    private String iStatus;
    @TableField("iid")
    private String iId;
    @TableField("lfurl")
    private String lfUrl;
}
