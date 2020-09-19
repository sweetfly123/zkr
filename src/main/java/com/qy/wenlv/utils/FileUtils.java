package com.qy.wenlv.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Barret
 * @description
 * @date 9/19/2020
 */
@Slf4j
@Component
public class FileUtils {

    @Value("${file.upload.catalog}")
    private String filePath;

    public String uploadFile(MultipartFile file) {
        //获取文件名
        String fileName = file.getOriginalFilename();
        fileName = getFileName(fileName);
        //采用配置文件配置目录
        String filepath = getUploadPath();
        if (!file.isEmpty()) {
            try {
                File fileDes = new File(filePath + File.separator + fileName);
                // 如果父目录不存在，创建父目录
                if (!fileDes.getParentFile().exists()) {
                    fileDes.getParentFile().mkdirs();
                }
                // 如果已存在,删除旧文件
                if (fileDes.exists()) {
                    fileDes.delete();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(fileDes);
                BufferedOutputStream out = new BufferedOutputStream(fileOutputStream);
                out.write(file.getBytes());
                out.flush();
                return "success";
            } catch (FileNotFoundException e) {
                return "文件找不到";
            } catch (IOException e) {
                return e.getMessage();
            }
        } else {
            return "文件为空";
        }
    }

    /**
     * 文件名后缀前添加一个时间戳
     */
    private String getFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        //设置时间格式
        final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyymmddHHmmss");
        //当前时间
        String nowTimeStr = sDateFormate.format(new Date());
        fileName = fileName.substring(0, index) + "_" + nowTimeStr + fileName.substring(index);
        return fileName;
    }

    /**
     * 获取当前系统路径
     */
    private String getUploadPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) {
            path = new File("");
        }
        File upload = new File(path.getAbsolutePath(), "static/upload/");
        if (!upload.exists()) {
            upload.mkdirs();
        }
        return upload.getAbsolutePath();
    }

    /**
     * 读取目录下的所有文件
     *
     * @param fileNames 保存文件名的集合
     * @return
     */
    public void findFileList(List<String> fileNames) {
        File dir = new File(filePath);
        // 判断是否存在目录
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 读取目录下的所有目录文件信息
        String[] files = dir.list();
        // 循环，添加文件名或回调自身
        for (int i = 0; i < files.length; i++) {
            File file = new File(dir, files[i]);
            // 如果文件
            if (file.isFile()) {
                // 添加文件全路径名
                fileNames.add(dir + "\\" + file.getName());
            }
            // 如果是目录
           /* else {
                // 回调自身继续查询
                findFileList(fileNames);
            }*/
        }
    }
}
