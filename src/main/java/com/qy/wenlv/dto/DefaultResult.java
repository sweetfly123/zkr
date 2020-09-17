package com.qy.wenlv.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qy.wenlv.baseinterface.ResultEnumService;
import com.qy.wenlv.constant.DefaultResultEnum;
import com.qy.wenlv.utils.EnumUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YunFengLiu
 */
@Data
public class DefaultResult<T> implements Serializable {

    private String code;

    private String msg;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss:SSS", timezone = "GMT+8")
    private Date currentTime = new Date();

    private T data;

    public DefaultResult() {
    }

    private <R extends ResultEnumService> DefaultResult(R resultEnum, T data) {
        this.code = EnumUtil.getCode(resultEnum);
        this.msg = EnumUtil.getMessage(resultEnum);
        this.data = data;
    }

    private <R extends ResultEnumService> DefaultResult(R resultEnum) {
        this.code = EnumUtil.getCode(resultEnum);
        this.msg = EnumUtil.getMessage(resultEnum);
        this.data = null;
    }


    private DefaultResult(String code,String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    private DefaultResult(T data) {
        this.data = data;
    }


    public static <T> DefaultResult<T> success(){
        return new DefaultResult<T>( DefaultResultEnum.SUCCESS,null);
    }
    public static <T> DefaultResult<T>  success(T data){
        return new DefaultResult<T>(DefaultResultEnum.SUCCESS,data);
    }

    public static <T> DefaultResult<T> fail(){
        return new DefaultResult<T>(DefaultResultEnum.ERROR_500,null);
    }

    public static <T, R extends ResultEnumService> DefaultResult<T> fail( R resultEnum){
        return new DefaultResult<T>(resultEnum,null);
    }

    public static DefaultResult fail (String code ,String msg){
        return  new DefaultResult(code ,msg);
    }

}
