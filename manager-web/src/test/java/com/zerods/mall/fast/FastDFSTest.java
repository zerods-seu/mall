package com.zerods.mall.fast;

import com.zerods.mall.common.utils.FastDFSClient;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author zerods
 * @version 1.0 05/09/2017
 */
public class FastDFSTest {
    @Test
    public void testUpload() throws IOException, MyException {
        // 创建一个配置文件，内容是tracker服务器的地址
        // 使用全局对象加载配置文件
        ClientGlobal.init(
                "/Users/admin/IdeaProjects/mall/manager-web/src/main/resources/config/client.conf");

        // 创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();

        // 通过TrackerClient获得一个TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();

        // 创建一个StorageServer的引用
        StorageServer storageServer = null;

        // 创建StorageClient，参数需要TrackerServer和StorageServer
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);

        String[] strings = storageClient.upload_file(
                "/Users/admin/Pictures/Irvue/table.jpg", "jpg", null);

        for (String s : strings) {
            System.out.println(s);
        }
    }

    @Test
    public void testFastDFSClient() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient(
                "/Users/admin/IdeaProjects/mall/manager-web/src/main/resources/config/client.conf");
        String s = fastDFSClient.uploadFile("/Users/admin/Pictures/4d94b00a-de39-4d78-b236-aa94c9daaa2d.jpg");
        System.out.println(s);
    }
}
