package com.qy.wenlv.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.qy.wenlv.entity.LFEntity;
import com.qy.wenlv.mapper.LFMapper;
import com.qy.wenlv.service.LFService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Barret
 * @description
 * @date 8/16/2020
 */
@Service
@Slf4j
public class LFServiceImpl extends ServiceImpl<LFMapper, LFEntity> implements LFService {
    @Autowired
    private LFMapper lfMapper;

    @Override
    public List<LFEntity> getLFInfo(Integer currentPage, Integer pageSize) {
        IPage<LFEntity> page = new Page<>(currentPage, pageSize);
//        page.setPages(2);
//        page.setSize(5);
        IPage<LFEntity> pageList = this.page(page);
        List<LFEntity> records = pageList.getRecords();
//        lfMapper.selectPage(1,"sdf");
//        PageHelper.startPage(2, 5);
//        List<LFEntity> lfEntities = lfMapper.listLfService();
        return records;
    }

    @Override
    public Boolean updateLFInfo(LFEntity entity) {
        return this.updateById(entity);
//        return this.save(entity);
    }

    /**
     * 功能描述 返回数据总数
     *
     * @return java.util.List<com.qy.wenlv.entity.LFEntity>
     * @author Barret
     * @date 8/22/2020
     */
    @Override
    public Integer getLFCount() {
        Integer count = lfMapper.selectCount(null);
        return count;
    }
}
