package com.xuecheng.media;

import io.minio.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    }

    /**
     * 上传分块文件
     * @throws Exception
     */
    @Test
    public void test_uploadChunk() throws Exception{
        String chunkFolderPath = "D:\\Java\\project\\xuechengzaixian\\bigfiles\\chunk\\";
        File chunkFolder = new File(chunkFolderPath);
        //分块文件
        File[] files = chunkFolder.listFiles();
        for (int i = 0; i < files.length; i++) {
            //上传文件的参数信息
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket("testbucket")//桶
                    .filename(chunkFolderPath + i)//指定本地文件路径
                    .object("chunk/" + i)//对象名
                    .build();

            //上传文件
            minioClient.uploadObject(uploadObjectArgs);
            System.out.println("上传分块" + i + "成功");
        }
    }

    /**
     * 合并文件，要求分块文件最小5M
     * @throws Exception
     */
    @Test
    public void test_merge() throws Exception {
        List<ComposeSource> sources = new ArrayList<>();
        String chunkFolderPath = "D:\\Java\\project\\xuechengzaixian\\bigfiles\\chunk\\";
        File chunkFolder = new File(chunkFolderPath);
        //分块文件
        File[] files = chunkFolder.listFiles();
        for (int i = 0; i < files.length; i++) {
            //指定分块文件的信息
            ComposeSource composeSource = ComposeSource.builder()
                    .bucket("testbucket")
                    .object("chunk/".concat(Integer.toString(i)))
                    .build();
            sources.add(composeSource);
        }

        //指定合并后的objectName信息
        ComposeObjectArgs composeObjectArgs = ComposeObjectArgs.builder()
                .bucket("testbucket")
                .object("merge01.mp4")
                .sources(sources)
                .build();
        minioClient.composeObject(composeObjectArgs);

    }

    /**
     * 清除分块文件
     */
    @Test
    public void test_removeObjects(){
        String chunkFolderPath = "D:\\Java\\project\\xuechengzaixian\\bigfiles\\chunk\\";
        File chunkFolder = new File(chunkFolderPath);
        //分块文件
        File[] files = chunkFolder.listFiles();
        //合并分块完成将分块文件清除
        List<DeleteObject> deleteObjects = Stream.iterate(0, i -> ++i)
                .limit(files.length)
                .map(i -> new DeleteObject("chunk/".concat(Integer.toString(i))))
                .collect(Collectors.toList());

        RemoveObjectsArgs removeObjectsArgs = RemoveObjectsArgs.builder().bucket("testbucket").objects(deleteObjects).build();
        Iterable<Result<DeleteError>> results = minioClient.removeObjects(removeObjectsArgs);
        results.forEach(r->{
            DeleteError deleteError = null;
            try {
                deleteError = r.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
