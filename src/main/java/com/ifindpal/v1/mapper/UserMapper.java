package com.ifindpal.v1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ifindpal.v1.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ansel
 * @since 2023-10-31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
