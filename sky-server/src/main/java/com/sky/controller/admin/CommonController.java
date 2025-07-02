package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * ClassName:CommonController
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/2下午2:54
 * @Version:
 */

@RestController
@RequestMapping("/admin/common")
@Api(tags = "公共接口")
@Slf4j
public class CommonController {

    @Autowired
    AliOssUtil aliOssUtil;

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("上传文件：{}", file);
        try {
            //生成文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString() + extension;
            String filepath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filepath);
        } catch (IOException e) {
            log.error("文件上传失败{}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
