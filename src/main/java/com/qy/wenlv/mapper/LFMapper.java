package com.qy.wenlv.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qy.wenlv.entity.LFEntity;
import com.qy.wenlv.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:lvyuanjie
 * @Date:2020/5/2 15:24
 */
@Repository
public interface LFMapper extends BaseMapper<LFEntity> {
    List<LFEntity> listLfService();
}

