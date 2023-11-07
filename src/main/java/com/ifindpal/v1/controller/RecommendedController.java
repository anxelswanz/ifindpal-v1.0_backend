package com.ifindpal.v1.controller;


import com.ifindpal.v1.entity.Recommended;
import com.ifindpal.v1.mapper.RecommendedMapper;
import com.ifindpal.v1.service.IRecommendedService;
import com.ifindpal.v1.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ansel
 * @since 2023-11-06
 */
@RestController
@RequestMapping("/recommended")
public class RecommendedController {

    @Autowired
    private IRecommendedService recommendedService;
    @GetMapping("/getRecommended")
    public RespBean getRecommended(){
        List<Recommended> list = recommendedService.list();
        return RespBean.success(list);
    }
}
