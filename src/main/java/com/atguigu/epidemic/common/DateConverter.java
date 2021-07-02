package com.atguigu.epidemic.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换器
 * 通过表单拿到的是字符串，但是数据库存贮的都是日期类型，每次单独转换很麻烦，写一个统一的日期的转换器
 * 将字符串自动转换为日期类型
 */

@Component
@Slf4j
public class DateConverter implements Converter<String, Date> {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 实现转换的方法
     *
     * @param s
     * @return
     */
    @Override
    public Date convert(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            log.error("转换的参数"+s+"为日期值时出错："+e.getMessage());
        }
        return date;

    }
}
