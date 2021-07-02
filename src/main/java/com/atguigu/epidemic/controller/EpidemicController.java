package com.atguigu.epidemic.controller;

import com.atguigu.epidemic.beans.AjaxResponseInfo;
import com.atguigu.epidemic.beans.EpidemicDetailInfo;
import com.atguigu.epidemic.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 疫情信息控制层
 * 构建--->构件
 */
@Controller
@RequestMapping("/epidemicData")
public class EpidemicController {
    @Autowired
    private EpidemicService epidemicService;

    /**
     * 获取所有疫情信息
     * json 返回前端，前后台分离
     */
    @GetMapping("/ajax/lastestData")
    @ResponseBody
    public AjaxResponseInfo findLatestestData() {
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        List<EpidemicDetailInfo> lastestData = epidemicService.findLastestData();
        responseInfo.setData(lastestData);
        return responseInfo;
    }
}
