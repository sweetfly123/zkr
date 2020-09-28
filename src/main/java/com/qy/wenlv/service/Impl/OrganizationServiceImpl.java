package com.qy.wenlv.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.wenlv.entity.Organization;
import com.qy.wenlv.mapper.OrganizationMapper;
import com.qy.wenlv.service.OrganizationService;
import org.springframework.stereotype.Service;

/**
 * @author Barret
 * @description
 * @date 9/27/2020
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {
}
