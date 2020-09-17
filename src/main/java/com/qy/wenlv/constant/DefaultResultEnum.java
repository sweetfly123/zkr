package com.qy.wenlv.constant;

import com.qy.wenlv.baseinterface.ResultEnumService;
import org.apache.commons.lang3.StringUtils;

public enum DefaultResultEnum implements ResultEnumService {

    SUCCESS("200", "请求成功"),
    ERROR_404("404", "请求路径不存在"),
    ERROR_401("401", "认证失败"),
    ERROR_500("500", "异常请求");

    private String code;
    private String message;

    private DefaultResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMsgByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (DefaultResultEnum defaultResultEnum : DefaultResultEnum.values()) {
                if (defaultResultEnum.getCode().equals(code)) {
                    return defaultResultEnum.getMessage();
                }
            }
        }
        return " ";
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

}
