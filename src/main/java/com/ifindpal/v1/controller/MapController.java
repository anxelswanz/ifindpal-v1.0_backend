package com.ifindpal.v1.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ifindpal.v1.entity.Map;
import com.ifindpal.v1.mapper.MapMapper;
import com.ifindpal.v1.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/map")
public class MapController {

    @Autowired
    private MapMapper mapMapper;

    @GetMapping("/getUsersByCity")
    public RespBean getUsersByCity(String currentCity){

        if (currentCity.contains("%20")) {
            currentCity  = currentCity.replaceAll("%20", " ");
        }

        QueryWrapper<Map> mapQueryWrapper = new QueryWrapper<>();
        mapQueryWrapper.eq("current_city",currentCity);
        List<Map> maps = mapMapper.selectList(mapQueryWrapper);
        return RespBean.success(maps);
    }

    @PostMapping("/setMeOnMap")
    public RespBean setMeOnMap(@RequestBody Map map){
        System.out.println(map);
        boolean ifShow = map.getIfShow();
        if(ifShow) {
            mapMapper.insert(map);
        }else {
            Map map1 = mapMapper.selectById(map);
            if (ObjectUtil.isNotEmpty(map1)){
                mapMapper.deleteById(map);
            }
        }
        return RespBean.success();
    }

    @GetMapping("/ifMeOnTheMap")
    public RespBean ifMeOnTheMap(String userId) {
        QueryWrapper<Map> mapQueryWrapper = new QueryWrapper<>();
        mapQueryWrapper.eq("user_id", userId);
        Map map = mapMapper.selectOne(mapQueryWrapper);
        if (ObjectUtil.isNotEmpty(map)){
            return RespBean.success(true);
        }else {
            return RespBean.success(false);
        }
    }
}
