package com.qy.wenlv.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.wenlv.entity.FilePath;
import com.qy.wenlv.mapper.FilePathMapper;
import com.qy.wenlv.service.FilePathService;
import org.springframework.stereotype.Service;

/**
 * @author Barret
 * @description
 * @date 9/27/2020
 */
@Service
public class FilePathServiceImpl extends ServiceImpl<FilePathMapper, FilePath> implements FilePathService {
}
