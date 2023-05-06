package com.sily.service.impl;

import com.sily.entity.LikeRecord;
import com.sily.mapper.LikeRecordMapper;
import com.sily.service.ILikeRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞记录 服务实现类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Service
public class LikeRecordServiceImpl extends ServiceImpl<LikeRecordMapper, LikeRecord> implements ILikeRecordService {

}
