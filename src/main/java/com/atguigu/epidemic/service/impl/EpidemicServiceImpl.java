package com.atguigu.epidemic.service.impl;

import com.atguigu.epidemic.beans.EpidemicDetailInfo;
import com.atguigu.epidemic.mapper.EpidemicMapper;
import com.atguigu.epidemic.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 疫情业务实现类
 */
@Service
@Transactional
public class EpidemicServiceImpl implements EpidemicService {
    @Autowired
    private EpidemicMapper epidemicMapper;

    @Override
    public List<EpidemicDetailInfo> findLastestData() {
        //查询每个省份的累计数量和当日新增数量
//        Calendar calendar = new GregorianCalendar();//获取当前日期
        short year = 0, month = 0, day = 0;
//        year = (short) calendar.get(Calendar.YEAR);
        year = 2020;
//        month = (short) calendar.get(Calendar.MONTH);
        month = 3;
//        day = (short) calendar.get(Calendar.DATE);
        day = 6;
        //将数据封装到Map中
        Map<String, Short> map = new HashMap<>();
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        List<EpidemicDetailInfo> lastestData = epidemicMapper.findLastestData(map);
        for (EpidemicDetailInfo e : lastestData) {
            System.out.println(e);
        }
        return lastestData;
    }
}
