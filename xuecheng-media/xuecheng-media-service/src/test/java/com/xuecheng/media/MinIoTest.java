package com.xuecheng.media;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.FilterInputStream;

/**
 * @author tangcw
 * @Desc 测试minio的sdk
 * @date 2023/4/7 9:47
 */
public class MinIoTest {

    MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://192.168.123.86:9000")
                    .credentials("admin", "admin123456")
                    .build();


    /**
     * 上传文件
     * @throws Exception
     */
    @Test
    public void test_upload() throws Exception{

        //根据扩展名取出mimeType
//        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(".mp4");
//        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;//通用mimeType，字节流

        //上传文件的参数信息
        UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                .bucket("testbucket")//桶
                .filename("D:\\Java\\project\\xuechengzaixian\\files\\1.jpg")//指定本地文件路径
                .object("1.jpg")//对象名
                .build();

        //上传文件
        minioClient.uploadObject(uploadObjectArgs);

    }

    /**
     * 删除文件
     * @throws Exception
     */
    @Test
    public void test_delete() throws Exception{

        //删除文件的参数信息
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket("testbucket")//桶
                .object("1.jpg")//对象名
                .build();

        //删除文件
        minioClient.removeObject(removeObjectArgs);
    }

    /**
     * 查询文件，从minio中下载
     * @throws Exception
     */
    @Test
    public void test_query() throws Exception{

        //查询文件的参数信息
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket("testbucket")//桶
                .object("1.jpg")//对象名
                .build();

        //查询文件
        FilterInputStream inputStream = minioClient.getObject(getObjectArgs);
        //指定输出流
        FileOutputStream outputStream = new FileOutputStream("D:\\Java\\project\\xuechengzaixian\\files\\1a.jpg");
        IOUtils.copy(inputStream, outputStream);

        //校验文件的完整性对文件的内容进行md5
//        String source_md5 = DigestUtils.md5Hex(inputStream);//minio中文件的md5
//        FileInputStream fileInputStream = new FileInputStream(new File("D:\\Java\\project\\xuechengzaixian\\files\\1a.jpg"));
//        String local_md5 = DigestUtils.md5Hex(fileInputStream);//下载文件的md5
//        if (source_md5.equals(local_md5)){
//            System.out.println("下载成功");
//        }
    }

}
