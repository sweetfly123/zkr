package com.qy.wenlv.controller;

import com.qy.wenlv.dto.DefaultResult;
import com.qy.wenlv.entity.Record;
import com.qy.wenlv.service.RecordService;
import com.qy.wenlv.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述
 *
 * @author Barret
 * @date 9/27/2020
 * @return
 */
@RestController
@Api(tags = "文件上传")
@RequestMapping("/api")
public class RecordController {

    @Autowired
    public RecordService recordService;

    @Autowired
    public FileUtils fileUtils;

    @ApiOperation(value = "上传多个附件", position = 1)
    @PostMapping(value = "/auploadFile", headers = "Content-Type=multipart/form-data")
    public DefaultResult uploadFile(@RequestParam("file") @ApiParam(value = "二进制文件流") MultipartFile[] files) {
        /*****************************/
        //业务层加入校验代码
        /*****************************/
        String msg = null;
        for (MultipartFile file : files) {
            msg = fileUtils.uploadFile(file);
        }
        return DefaultResult.success(msg);
    }

    @ApiOperation(value = "上传单个文件", position = 2)
    @PostMapping(value = "/uploadSingleFile")
    public DefaultResult uploadSingleFile(@RequestParam("file") @ApiParam(value = "二进制文件流", required = true) MultipartFile file,
                                          Record record,
                                          @RequestParam(value = "path") @ApiParam(value = "上传路径", required = true) String path) {
        /*****************************/
        //业务层加入校验代码
        /*****************************/
        recordService.saveOrUpdate(record);
        String msg = fileUtils.uploadFile(file);
        return DefaultResult.success(msg);
    }

    @ApiOperation(value = "查看路径下的所有文件", position = 1)
    @GetMapping(value = "/list/file")
    public DefaultResult getFileList() {
        List<String> fileNames = new ArrayList<>();
        fileUtils.findFileList(fileNames);
        return DefaultResult.success(fileNames);
    }


    @ApiOperation(value = "查看上传记录")
    @GetMapping(value = "/list/record")
    public DefaultResult getRecord() {
        List<Record> list = recordService.list();
        return DefaultResult.success(list);
    }
}