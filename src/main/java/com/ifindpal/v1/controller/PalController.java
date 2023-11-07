package com.ifindpal.v1.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ifindpal.v1.entity.Pal;
import com.ifindpal.v1.entity.User;
import com.ifindpal.v1.mapper.PalMapper;
import com.ifindpal.v1.mapper.UserMapper;
import com.ifindpal.v1.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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
@RequestMapping("/pal")
public class PalController {

    @Autowired
    private PalMapper palMapper;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/getPals")
    public RespBean getPals(String userId){
        QueryWrapper<Pal> palQueryWrapper = new QueryWrapper<>();
        palQueryWrapper.eq("user_id",userId);
        List<Pal> pals = palMapper.selectList(palQueryWrapper);
        ArrayList<User> users = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        User firstMet = null;
        if (ObjectUtil.isNotEmpty(pals)) {
            for (Pal pal : pals) {
                QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
                userQueryWrapper.eq("user_id",pal.getPalId());
                User user = userMapper.selectOne(userQueryWrapper);
                user.setPassword(null);
                user.setEmail(null);
                users.add(user);
                String first = pal.getMatchId().substring(0, 1);
                if (pal.getIfShow() == 0 && first.equals("B")) {
                    firstMet = user;
                    pal.setIfShow(1);
                    palMapper.updateById(pal);
                    map.put("firstMet", firstMet.getNickName());
                }
            }
            map.put("pals", users);
            return RespBean.success(map);
        }else {
            return RespBean.success();
        }
    }
}
