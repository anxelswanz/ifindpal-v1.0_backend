package com.ifindpal.v1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ifindpal.v1.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ansel
 * @since 2023-11-06
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
