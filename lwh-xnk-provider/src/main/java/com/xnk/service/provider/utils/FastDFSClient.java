package com.xnk.service.provider.utils;


import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import com.xnk.service.provider.properties.FastdfsProperties;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class FastDFSClient {

	private static Logger logger = LoggerFactory.getLogger(FastDFSClient.class);
	
	//fdfs_client 核心配置文件
   public String conf_filename = "src/main/resources/fdfs_client.conf"; 
    
   private static TrackerClient trackerClient = null;
   private static TrackerServer trackerServer = null;
   private static StorageServer storageServer = null;
   private static StorageClient1 storageClient = null;

   private static Properties prop = new Properties();

   private static FastdfsProperties  fdfsProp = new FastdfsProperties();

   static {
       try {
		   prop.put("fastdfs.connect_timeout_in_seconds", fdfsProp.getConnectTimeout());
prop.put("fastdfs.network_timeout_in_seconds", fdfsProp.getNetworkTimeout());
prop.put("fastdfs.charset", fdfsProp.getCharset());
prop.put("fastdfs.http_anti_steal_token", fdfsProp.getAntiStealToken());
prop.put("fastdfs.http_secret_key", fdfsProp.getHttpSecretKey());
prop.put("fastdfs.http_tracker_http_port", fdfsProp.getHttpTrackerHttpPort());
prop.put("fastdfs.tracker_servers", fdfsProp.getTrackerServer());
ClientGlobal.initByProperties(prop);

           trackerClient = new TrackerClient();
           trackerServer = trackerClient.getConnection();
           storageServer = null;
           storageClient = new StorageClient1(trackerServer, storageServer);
       } catch (Exception e) {
           logger.error("FastDFS Client Init Fail!",e);
       }
   }

   public FastDFSClient() throws Exception {
   }
   
   /**
    * 上传文件方法
    * <p>Title: uploadFile</p>
    * <p>Description: </p>
    * @param fileName 文件全路径
    * @param extName 文件扩展名，不包含（.）
    * @param metas 文件扩展信息
    * @return
    * @throws Exception
    */
   public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
      String result = storageClient.upload_file1(fileName, extName, metas);
      return result;
   }
   
   public String uploadFile(String fileName) throws Exception {
      return uploadFile(fileName, null, null);
   }
   
   public String uploadFile(String fileName, String extName) throws Exception {
      return uploadFile(fileName, extName, null);
   }
   
   public String uploadFile(MultipartFile file, String extName) throws Exception {
		return uploadFile(file.getBytes(),extName,null);
	}
   
   /**
    * 上传文件方法
    * <p>Title: uploadFile</p>
    * <p>Description: </p>
    * @param fileContent 文件的内容，字节数组
    * @param extName 文件扩展名
    * @param metas 文件扩展信息
    * @return
    * @throws Exception
    */
   public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
      
      String result = "";
      try{
      	logger.info("storageClient=>"+storageClient+",fileContent=>"+fileContent);
    	  result = storageClient.upload_file1(fileContent, extName, metas);
      }catch(Exception e){
    	  result = storageClient.upload_file1(fileContent, extName, metas);
      }
      logger.info("result=>"+result);
      
      return result;
   }
   
   public String uploadFile(byte[] fileContent) throws Exception {
      return uploadFile(fileContent, null, null);
   }
   
   public String uploadFile(byte[] fileContent, String extName) throws Exception {
      return uploadFile(fileContent, extName, null);
   }
   
	public InputStream downFile(String groupName, String remoteFileName) throws Exception{
		byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
		InputStream ins = new ByteArrayInputStream(fileByte);
		return ins;
	}
	
	public boolean downloadFile(String dfilePath, String dfileName, HttpServletResponse response) throws Exception{
		InputStream ins = null;
		OutputStream toClient = null;
		byte[] fileByte = null;
		try{
			fileByte = storageClient.download_file1(dfilePath);
		}catch(Exception e	){
			logger.error("连接下载异常",e);
		}
		try{
			if(null == fileByte){
				fileByte = storageClient.download_file1(dfilePath);
			}
			ins = new ByteArrayInputStream(fileByte);
			
			// 清空response
			response.reset();
			//对文件名作处理，避免中文乱码问题
			dfileName = new String(dfileName.getBytes("utf8"),"iso8859-1");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + dfileName);
			
			toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(fileByte);
			toClient.flush();
		}catch(Exception e){
			logger.error("fastdfs 下载出错!",e);
			return false;
		}finally{
			try {
				if(null != ins)
					ins.close();
				
				if(null != toClient){
					toClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
      }
		
		return true;
	}
	
   
	public void deleteFile(String groupName, String remoteFileName)
	        throws Exception {
	    int i = storageClient.delete_file(groupName, remoteFileName);
	    logger.info("delete file successfully!!!" + i);
	}
	
   public static void main(String[] args) {
	   /**
	    * FastDFS工具类使用
	    * @auther: xushuai
	    * @date: 2018/5/14 20:21
	    * @return:
	    * @throws:
	    */
	       //使用Tracker服务器信息配置文件位置构造工具类对象
	       FastDFSClient util;
		try {
			util = new FastDFSClient();
			//上传文件到文件服务器
		       String s = util.uploadFile("C:\\Users\\msht\\Desktop\\img\\timg (1).jpg");
		       //打印文件在服务器所在位置
		       System.out.print(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
}


}
