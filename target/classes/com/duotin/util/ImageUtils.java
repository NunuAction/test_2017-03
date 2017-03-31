package com.duotin.util;

import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author jared
 * @Description:图片相关工具类
 * @date Dec 1, 2014 3:40:16 PM
 */
public final class ImageUtils {

    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);
    private static final String IMAG_UPLOAD_URL = "http://fs02.duotin.com/file/image/in?t=1417422831&k=533f100077a1a0c80e9c8f2acc42049f";
    private static final String DEFAULT_IAMGE = "http://c3.duotin.com/i1/DT/fu8symKNqxXRNRCvMBALDYGQgerWNtzC7Kj0ptyqV94.jpg___s400x300.jpg";

    public static String uploadImageToDuotin(final File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        return uploadImageToDuotin(new PartSource() {
            @Override
            public long getLength() {
                return file.length();
            }

            @Override
            public String getFileName() {
                return file.getName();
            }

            @Override
            public InputStream createInputStream() throws IOException {
                return new FileInputStream(file);
            }
        });
    }

    public static String uploadImageToDuotin(PartSource fileSource) {
        try {
            FilePart part = new FilePart("uf", fileSource);
            String json ="";
            String url="";
            int count=0;

            while (StringUtil.isEmpty(url)&&count<=3){
                count++;
                try{
                    json = HttpUtils.fileUpload(
                            IMAG_UPLOAD_URL,
                            new Part[]{part});
                    json = json.replaceAll("\n", "").replaceAll("\r", "").replaceAll("﻿", "");
                    if(StringUtils.isNotEmpty(json)){
                        Map<String, Object> map = JsonUtils.toMap(json);
                        if(map.containsKey("url")){
                            url= map.get("url")+"";
                        }
                    }
                    log.info("upload image to duotin json: " + json);
                }catch (Exception e){
                    log.error("upload image to duotin has a error !" , e);
                }
            }
            return url;
        } catch (Throwable e) {
            log.error("upload image to duotin has a error !" , e);
        }
        return ConstStrings.EMPTY;
    }

    public static String showImage(String imagePath) {
        if (StringUtils.isNotEmpty(imagePath)) {
            return imagePath;
        }
        return DEFAULT_IAMGE;
    }

    public static String showImage(String imagePath, String defaultValue) {
        if (StringUtils.isNotEmpty(imagePath)) {
            return imagePath;
        }
        if (StringUtils.isNotEmpty(defaultValue)) {
            return defaultValue;
        }
        return DEFAULT_IAMGE;
    }


    /**
     * 获取多听图片服务完整链接链接
     *
     * @param source 数据库获取的图片链接
     *               是否是开发环境
     * @return
     */
    public static String getDuotinImageUrlByWechat(String source, String host) {
        if (StringUtils.isNotEmpty(source)) {
            if (source.startsWith("http")) {
                source = source.replaceAll("http://c[0-9]\\.duotin\\.com/", host);
            } else {
                if (source.startsWith("static/")) {
                    source = host + source;
                } else {
                    source = host + "static/" + source;
                }
            }
            return source;
        }
        return ConstStrings.EMPTY;
    }

    public static final String DUOTIN_IMAGE__HOST_C11 = "http://c11.duotin.com/";

    /**
     * static 转换为 http地址
     *
     * @param source
     * @return
     */
    public static String getDuotinImageUrl(String source) {
        String result = ConstStrings.EMPTY;
        if (StringUtils.isEmpty(source)) {
            return source;
        }
        if (source.startsWith("http")) {
            result = source.replaceAll("http://c1\\.duotin\\.com/", DUOTIN_IMAGE__HOST_C11);
        } else {
            if (source.startsWith("static/")) {
                result = DUOTIN_IMAGE__HOST_C11 + source;
            } else {
                result = DUOTIN_IMAGE__HOST_C11 + "static/" + source;
            }
        }
        return result;
    }

    /**
     * 上传微信图片到多听服务器
     *
     * @param rootPath
     * @param url      图片存放在微信服务器的url
     * @return
     */
    public static String uploadWeixinImageToDuotin(String rootPath, String url) {
        if (StringUtils.isNotEmpty(url, rootPath)) {
            StringBuilder path = new StringBuilder();
            path.append(rootPath);
            path.append("headingImg_");
            path.append(System.currentTimeMillis());
            path.append(".jpg");
            if (HttpUtils.downloadFile(url, path.toString())) {
                File file = new File(path.toString());
                String duotinImgUrl = "";
                if (file.exists()) {
                    duotinImgUrl = ImageUtils.uploadImageToDuotin(file);
                    file.delete();
                }
                return duotinImgUrl;
            }
        }
        return ConstStrings.EMPTY;
    }


}
