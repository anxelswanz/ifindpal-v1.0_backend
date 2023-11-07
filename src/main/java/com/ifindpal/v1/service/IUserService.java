package com.ifindpal.v1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ifindpal.v1.entity.User;
import com.ifindpal.v1.vo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ansel
 * @since 2023-10-31
 */
public interface IUserService extends IService<User> {
    public RespBean login(User user);
   public  RespBean logout() ;
}
