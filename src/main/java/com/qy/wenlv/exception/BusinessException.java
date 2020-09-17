package com.qy.wenlv.exception;

import com.qy.wenlv.baseinterface.ResultEnumService;
import com.qy.wenlv.utils.EnumUtil;
import lombok.Data;

/**
 * @author YunFengLiu
 */
@Data
public class BusinessException extends RuntimeException {
    private String code;
    private String msg;


    public  <R extends ResultEnumService> BusinessException(R resultEnum) {
        this.code = EnumUtil.getCode(resultEnum);
        this.msg = EnumUtil.getMessage(resultEnum);
    }


}
