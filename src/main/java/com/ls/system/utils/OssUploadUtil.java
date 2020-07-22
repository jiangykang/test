package com.ls.system.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.ls.system.config.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Min.Hu
 * Time: 2020/4/27 14:21
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public class OssUploadUtil {

    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;
    private static String projectName;
    private static String ossurl;
    // 文件类型
    private static List<String> officeFileTypes = new ArrayList<String>();
    // 文件类型
    private static List<String> videoFileTypes = new ArrayList<String>();
    private static List<String> imageFileTypes = new ArrayList<String>();
    private static OSSClient ossClient = null;

    static {
        officeFileTypes.add(".doc");
        officeFileTypes.add(".docx");
        officeFileTypes.add(".xls");
        officeFileTypes.add(".xlsx");
        officeFileTypes.add(".txt");

        videoFileTypes.add(".mp4");
        videoFileTypes.add(".flv");
        videoFileTypes.add(".f4v");
        videoFileTypes.add(".m3u8");

        imageFileTypes.add(".jpg");
        imageFileTypes.add(".jpeg");
        imageFileTypes.add(".bmp");
        imageFileTypes.add(".gif");
        imageFileTypes.add(".png");

    }

    public static void setOssurl(String ossurl) {
        OssUploadUtil.ossurl = ossurl;
    }

    public static void setEndpoint(String endPoint) {
        OssUploadUtil.endpoint = endPoint;
    }

    public static void setAccessKeyId(String accessKeyId) {
        OssUploadUtil.accessKeyId = accessKeyId;
    }

    public static void setAccessKeySecret(String accessKeySecret) {
        OssUploadUtil.accessKeySecret = accessKeySecret;
    }

    public static void setBucketName(String bucketName) {
        OssUploadUtil.bucketName = bucketName;
    }

    public static void setProjectName(String projectName) {
        OssUploadUtil.projectName = projectName;
    }

    public static OSSClient initOSS() {
        if (ossClient == null) {
            ossClient = new OSSClient(endpoint,
                    new DefaultCredentialProvider(accessKeyId, accessKeySecret),
                    new ClientConfiguration());
        }
        return ossClient;
    }

    /**
     * @param file
     * @return
     */
    public static ResponseResult upload(MultipartFile file) {

        return upload(file, false);
    }

    public static ResponseResult upload(MultipartFile file, Boolean isRandomName) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String uploadFilePath = isRandomName ? projectName + "/" + GenerateIdUtil.generateId() + "." + suffix
                : projectName + "/" + originalFilename;
        return upload(file, uploadFilePath);
    }

    /**
     * @param file
     * @param directoryName 上传的文件夹名称,如果需要将上传的文件分开放到不同的文件夹,用此方法 分隔符用/
     * @param isRandomName  是否采用随机名 true 采用,false 不采用
     * @return
     */
    public static ResponseResult upload(MultipartFile file, String directoryName, Boolean isRandomName) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String uploadFilePath = isRandomName
                ? projectName + "/" + directoryName + "/" + GenerateIdUtil.generateId() + "." + suffix
                : projectName + "/" + directoryName + "/" + originalFilename;
        return upload(file, uploadFilePath);
    }

    /**
     * 文件上传
     *
     * @param file 文件
     * @return uploadFilePath
     * @Title upload
     * @author xzm
     * @date 20190611
     */
    public static ResponseResult upload(MultipartFile file, String uploadFilePath) {

        JSONObject jsonObject = new JSONObject();

        ResponseResult responseResult = null;

        if (!file.isEmpty()) {
            try {
                ossClient.putObject(bucketName, uploadFilePath, file.getInputStream());
                jsonObject.put("url", ossurl + uploadFilePath);// 返回存储后的url
                jsonObject.put("originalFilename", file.getOriginalFilename());

                responseResult = ResponseResult.success(jsonObject);
            } catch (Exception e) {
                responseResult = ResponseResult.failure(e.getMessage());
            }

        } else {
            responseResult = ResponseResult.failure("文件为空,请检查");

        }

        return responseResult;
    }

    /**
     * @param file
     * @return
     */
    public static ResponseResult upload(File file) {
        return upload(file, false);
    }

    public static ResponseResult upload(File file, Boolean isRandomName) {
        String originalFilename = file.getName();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String uploadFilePath = isRandomName ? projectName + GenerateIdUtil.generateId() + "." + suffix
                : projectName + originalFilename;
        return upload(file, uploadFilePath);
    }

    /**
     * @param file
     * @param directoryName 上传的文件夹名称,如果需要将上传的文件分开放到不同的文件夹,用此方法 分隔符用/
     * @param isRandomName  是否采用随机名 true 采用,false 不采用
     * @return
     */
    public static ResponseResult upload(File file, String directoryName, Boolean isRandomName) {
        String originalFilename = file.getName();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String uploadFilePath = isRandomName ? projectName + directoryName + GenerateIdUtil.generateId() + "." + suffix
                : projectName + directoryName + originalFilename;
        return upload(file, uploadFilePath);
    }

    public static ResponseResult upload(File file, String uploadFilePath) {

        JSONObject jsonObject = new JSONObject();

        ResponseResult responseResult = null;

        if (!file.isFile()) {

            try {

                ossClient.putObject(bucketName, uploadFilePath, file);
                jsonObject.put("url", ossurl + uploadFilePath);// 返回存储后的url
                jsonObject.put("originalFilename", file.getName());

                responseResult = ResponseResult.success(jsonObject);
            } catch (Exception e) {
                responseResult = ResponseResult.failure(e.getMessage());
            }

        } else {
            responseResult = ResponseResult.failure("文件为空,请检查");

        }

        return responseResult;
    }

    /**
     * 删除服务器上的文件
     *
     * @param uploadFileUrl
     */
    public static void deleteFile(String uploadFileUrl) {
        // 创建OSSClient实例。
        ossClient.deleteObject(bucketName, uploadFileUrl);
    }

    /**
     * 或取流
     *
     * @param requestUrl
     */
    public static InputStream getFileInputStream(String requestUrl) {
        // 创建OSSClient实例。

        InputStream inputStream = null;
        URL url = null;
        HttpURLConnection httpUrlConn = null;
        // 设置请求方式（GET/POST）
        try {
            url = new URL(requestUrl);
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod("get");
            httpUrlConn.connect();
            // 将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            httpUrlConn.disconnect();
        }

        return inputStream;

    }


}
