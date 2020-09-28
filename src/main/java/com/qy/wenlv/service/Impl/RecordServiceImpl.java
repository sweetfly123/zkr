package com.qy.wenlv.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.wenlv.entity.Record;
import com.qy.wenlv.mapper.RecordMapper;
import com.qy.wenlv.service.RecordService;
import org.springframework.stereotype.Service;

/**
 * @author Barret
 * @description
 * @date 9/27/2020
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
}
