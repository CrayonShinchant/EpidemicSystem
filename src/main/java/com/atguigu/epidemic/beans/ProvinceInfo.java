package com.atguigu.epidemic.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 省份
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProvinceInfo {
    private Integer provinceId;
    private String provinceName;//省份名称
    private String provincePinYin;//省份拼音标识

}
