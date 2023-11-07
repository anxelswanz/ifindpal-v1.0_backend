package com.ifindpal.v1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ifindpal.v1.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ansel
 * @since 2023-11-05
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

}
