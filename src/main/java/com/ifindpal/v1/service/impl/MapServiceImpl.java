package com.ifindpal.v1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ifindpal.v1.entity.Map;
import com.ifindpal.v1.mapper.MapMapper;
import com.ifindpal.v1.service.IMapService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ansel
 * @since 2023-11-06
 */
@Service
public class MapServiceImpl extends ServiceImpl<MapMapper, Map> implements IMapService {

}
