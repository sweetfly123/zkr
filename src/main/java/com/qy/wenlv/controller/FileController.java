package com.qy.wenlv.controller;

import com.qy.wenlv.dto.DefaultResult;
import com.qy.wenlv.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jiashubing
 * @since 2019/6/25
 */
@RestController
@Api(tags = "文件上传")
@RequestMapping("/api")
public class FileController {

    @Autowired
    public FileUtils fileUtils;

    @ApiOperation("上传多个附件")
    @PostMapping(value = "/uploadFile", headers = "Content-Type=multipart/form-data")
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

    @ApiOperation("上传单个文件")
    @PostMapping(value = "/uploadSingleFile")
    public DefaultResult uploadSingleFile(@RequestParam("file") @ApiParam(value = "二进制文件流") MultipartFile file) {
        /*****************************/
        //业务层加入校验代码
        /*****************************/
        String msg = fileUtils.uploadFile(file);
        return DefaultResult.success(msg);
    }

    @ApiOperation("查看路径下的所有文件")
    @GetMapping(value = "/list/file")
    public DefaultResult getFileList() {
        List<String> fileNames = new ArrayList<>();
        fileUtils.findFileList(fileNames);
        return DefaultResult.success(fileNames);
    }

}