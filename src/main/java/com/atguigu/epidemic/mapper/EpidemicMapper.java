package com.atguigu.epidemic.mapper;

import com.atguigu.epidemic.beans.EpidemicDetailInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 疫情信息数据访问层（对数据库增删改查）
 */
@Mapper
public interface EpidemicMapper {
    /**
     * 根据日期（年月日）查询疫情信息
     */
    @Select(value ="SELECT el.province_id,temp.province_name,el.data_year,el.data_month,el.data_day, " +
            " temp.affirmed_total,temp.suspected_total,temp.isolated_total,temp.cured_total, " +
            " temp.dead_total " +
            " from " +
            "epidemics el RIGHT OUTER JOIN ( " +
            " SELECT e.province_id,p.province_name,SUM(e.affirmed) affirmed_total, " +
            " SUM(e.suspected) suspected_total,SUM(e.isolated) isolated_total, " +
            " SUM(e.cured) cured_total,SUM(e.dead) dead_total " +
            " from epidemics e RIGHT OUTER JOIN provinces p " +
            " on e.province_id=p.province_id " +
            " GROUP BY e.province_id,p.province_name) temp " +
            "on el.province_id = temp.province_id " +
            "where el.data_year = #{year} " +
            "and el.data_month = #{month} " +
            "and el.data_day = #{day}")
    List<EpidemicDetailInfo> findLastestData(Map<String,Short> condition);
}
