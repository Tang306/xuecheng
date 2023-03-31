package com.xuecheng.base.execption;

import java.io.Serializable;

/**
 * @author tangcw
 * @Desc 错误响应参数包装
 * @date 2023/3/31 16:12
 */
public class RestErrorResponse implements Serializable {
    private String errMessage;

    public RestErrorResponse(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
