package com.abtc.netty.pojo;

import io.netty.handler.codec.http.multipart.FileUpload;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * 封装请求对象，保存对应的传参数。
 */
public class MultipartRequest {
    private Map<String, FileUpload> fileUploads;
    private JSONObject params;

    public Map<String, FileUpload> getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(Map<String, FileUpload> fileUploads) {
        this.fileUploads = fileUploads;
    }

    public JSONObject getParams() {
        return params;
    }

    public void setParams(JSONObject params) {
        this.params = params;
    }

}
