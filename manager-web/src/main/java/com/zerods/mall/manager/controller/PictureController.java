package com.zerods.mall.manager.controller;

import com.alibaba.fastjson.JSON;
import com.zerods.mall.common.utils.FastDFSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zerods
 * @version 1.0 06/09/2017
 */
@Controller
public class PictureController {

    private static final Logger LOG = LoggerFactory.getLogger(PictureController.class);

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping(value = "/pic/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile) {
        Map<String, Object> result = new HashMap<>();
        // 把图片上传到图片服务器
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/client.conf");
            // 取得文件的扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            // 得到图片的地址和文件名
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            // 完整url
            url = IMAGE_SERVER_URL + url;
            // 封装到Map中返回
            result.put("error", 0);
            result.put("url", url);
        } catch (Exception e) {
            LOG.error("图片上传失败");
            result.put("error", 1);
            result.put("url", "图片" + uploadFile.getOriginalFilename() + "上传失败");
        }

        return JSON.toJSONString(result);
    }
}
