package com.atguigu.epidemic.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 疫情信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EpidemicInfo {
    private Integer serialId;
    private Integer provinceId;//省份编号
    private Short dataYear;
    private Short dataMonth;
    private Short dataDay;
    //确诊人数，疑似人数，治愈人数，隔离人数，死亡人数(每一天)
    private Integer affirmed,suspected,cured,isolated,dead;
    private Integer userId;//用户编号
    private Date inputDate;//录入时间

}
