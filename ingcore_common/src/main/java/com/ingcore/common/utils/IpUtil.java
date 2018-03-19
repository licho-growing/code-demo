package com.ingcore.common.utils;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	/**
     * 获取远程访问IP
     */
    private String remoteIp = null;
	/**
     * 获取远程访问IP
     * @return
     */
    public String getRemoteIp(HttpServletRequest request){
        if (this.remoteIp==null || this.remoteIp.length()==0){
            this.remoteIp = request.getHeader("x-forwarded-for");
            if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
                this.remoteIp= request.getHeader("X-Real-IP");
            }
            if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
                this.remoteIp= request.getHeader("Proxy-Client-IP");
            }
            if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
                this.remoteIp= request.getHeader("WL-Proxy-Client-IP");
            }
            if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
                this.remoteIp= request.getHeader("HTTP_CLIENT_IP");
            }
            if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
                this.remoteIp= request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
                this.remoteIp= request.getRemoteAddr();
            }
            if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
                this.remoteIp= request.getRemoteHost();
            }
            if(this.remoteIp.equals("0:0:0:0:0:0:0:1")){
            	this.remoteIp = "127.0.0.1";
            }
        }
        return remoteIp;
    }
}
