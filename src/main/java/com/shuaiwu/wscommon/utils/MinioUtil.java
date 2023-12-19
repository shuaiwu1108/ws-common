package com.shuaiwu.wscommon.utils;

import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * minio工具类 2023-12-10 14:09
 */
@Slf4j
@Component
public class MinioUtil {
    private static String miniourl;
    private static String miniouser;
    private static String miniopassword;

    public static void init(String url, String user, String password){
        miniourl = url;
        miniouser = user;
        miniopassword = password;
    }

    /**
     *
     * @param bucket
     * @param objName
     * @return
     */
    public static InputStream getObject(String bucket, String objName)
        throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        InputStream inputStream = createClient().getObject(GetObjectArgs.builder()
            .bucket(bucket)
            .object(objName)
            .build());
        return inputStream;
    }

    /**
     * @param bucket  存储在minio的目录
     * @param objName 存储在minio的文件名
     * @param is      流
     */
    public static boolean putObject(String bucket, String objName, InputStream is) {
        try {
            createClient().putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(objName)
                .stream(is, is.available(), -1)
                .build());
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
        return true;
    }

    private static MinioClient createClient() {
        return MinioClient.builder()
            .endpoint(miniourl)
            .credentials(miniouser, miniopassword)
            .build();
    }

    private static MinioClient createBucket(String bucket)
        throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = createClient();
        boolean exists = minioClient.bucketExists(
            BucketExistsArgs.builder().bucket(bucket).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
        return minioClient;
    }
}
