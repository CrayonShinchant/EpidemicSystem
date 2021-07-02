package com.atguigu.epidemic.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Ajax传递的数据格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AjaxResponseInfo<T> {
    private int code;// -1:参数不足 -2:权限不足 0:正常应答
    private String msg;//应答消息
    private T data;//传递的具体数据

}
