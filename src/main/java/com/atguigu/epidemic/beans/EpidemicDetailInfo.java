package com.atguigu.epidemic.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 根据需求对疫情信息进行扩充，供显示使用
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EpidemicDetailInfo extends EpidemicInfo {
    private String provinceName;
    //确诊，疑似，治愈，隔离，死亡（累计数据） DDL DCL DQL DML
    private Integer affirmedTotal,suspectedTotal,isolatedTotal,curedTotal,deadTotal;

}
