package com.xnk.service.provider;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = XnkApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XnkTest {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	//fdfs_client 核心配置文件
	   public String conf_filename = "src/main/resources/fdfs_client.conf"; 
	    
	   private static TrackerClient trackerClient = null;
	   private static TrackerServer trackerServer = null;
	   private static StorageServer storageServer = null;
	   private static StorageClient1 storageClient = null;
	   
	@Test
    public void test2() throws Exception {
		try {
	           String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();;
	           ClientGlobal.init(filePath);
	           trackerClient = new TrackerClient();
	           trackerServer = trackerClient.getConnection();
	           storageServer = null;
	           storageClient = new StorageClient1(trackerServer, storageServer);
	           System.err.println(storageClient);
	       } catch (Exception e) {
	           logger.error("FastDFS Client Init Fail!",e);
	       }
	}
	
}
