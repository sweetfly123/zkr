package com.qy.wenlv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Barret
 * @description
 * @date 8/7/2020
 */
@Data
@ApiModel("单条记录")
@TableName("record")
public class Record implements Serializable {
    //    private static final long serialVersionUID = -8478114427891717226L;
    @ApiModelProperty(hidden = true)
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    //用户id
    @ApiModelProperty(value = "用户ID", example = "0000", position = 1, required = true)
    @TableField(value = "user_id")
    private Integer userId;
    //提供人
    @ApiModelProperty(value = "提供人", position = 2, required = true)
    private String provider;
    //类型：增量/全量
    @ApiModelProperty(value = "类型", example = "1", position = 3, required = true)
    private int type;
    @ApiModelProperty(value = "是否加密", example = "0", position = 4, required = true)
    private int isSecret;

    @ApiModelProperty(hidden = true)
    private String fileName;
    @ApiModelProperty(hidden = true)
    private int isModifyHeader;
    @ApiModelProperty(hidden = true)
    private Date commitTime;
    @ApiModelProperty(hidden = true)
    private String fileSize;
    @ApiModelProperty(hidden = true)
    private String fileFormat;
    @ApiModelProperty(hidden = true)
    private String status;
    @ApiModelProperty(hidden = true)
    private String detail;
}
