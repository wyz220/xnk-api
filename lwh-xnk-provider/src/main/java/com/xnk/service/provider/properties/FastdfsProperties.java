package com.xnk.service.provider.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:fdfs_client.properties"},ignoreResourceNotFound=false,encoding="UTF-8")
//@ConfigurationProperties(prefix = "fastdfs")
public class FastdfsProperties {

    private String connectTimeout = "10";
    private String networkTimeout = "30";
    private String charset = "UTF-8";
    private String antiStealToken = "no";
    private String httpTrackerHttpPort = "8888";
    private String httpSecretKey = "FastDFS1234567890";
    private String trackerServer = "120.78.0.145:22122";

    public String getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(String connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getNetworkTimeout() {
        return networkTimeout;
    }

    public void setNetworkTimeout(String networkTimeout) {
        this.networkTimeout = networkTimeout;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getAntiStealToken() {
        return antiStealToken;
    }

    public void setAntiStealToken(String antiStealToken) {
        this.antiStealToken = antiStealToken;
    }

    public String getHttpTrackerHttpPort() {
        return httpTrackerHttpPort;
    }

    public void setHttpTrackerHttpPort(String httpTrackerHttpPort) {
        this.httpTrackerHttpPort = httpTrackerHttpPort;
    }

    public String getHttpSecretKey() {
        return httpSecretKey;
    }

    public void setHttpSecretKey(String httpSecretKey) {
        this.httpSecretKey = httpSecretKey;
    }

    public String getTrackerServer() {
        return trackerServer;
    }

    public void setTrackerServer(String trackerServer) {
        this.trackerServer = trackerServer;
    }
}
