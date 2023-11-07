package com.ifindpal.v1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ifindpal.v1.entity.Tag;
import com.ifindpal.v1.mapper.TagMapper;
import com.ifindpal.v1.service.ITagService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ansel
 * @since 2023-11-04
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
