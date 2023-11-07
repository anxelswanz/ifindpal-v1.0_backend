package com.ifindpal.v1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ifindpal.v1.entity.Message;
import com.ifindpal.v1.mapper.MessageMapper;
import com.ifindpal.v1.service.IMessageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ansel
 * @since 2023-11-05
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
