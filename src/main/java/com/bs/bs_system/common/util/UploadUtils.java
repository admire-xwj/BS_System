package com.bs.bs_system.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

/**
 * 文件上传工具
 */
@Slf4j
@Component
public class UploadUtils {


    /**
     * 下载模板
     *
     * @param httpServletRequest
     * @param response
     * @param name               文件名
     * @param path               文件路径
     * @return
     * @throws Exception
     */
    public ServletOutputStream downFileForMoulds(HttpServletRequest httpServletRequest, HttpServletResponse response, String name, String path) throws Exception {

        File file = null;
        file = new File(path);
        if (!file.isFile()) {
            file = new File(path, name);
        }

        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = null;
        if (httpServletRequest.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
            downloadFielName = new String(name.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器
        } else if (httpServletRequest.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0 ||
                httpServletRequest.getHeader("User-Agent").indexOf("like Gecko") > 0) {
            downloadFielName = URLEncoder.encode(name, "UTF-8");// IE浏览器
        } else if (httpServletRequest.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
            downloadFielName = new String(name.getBytes("UTF-8"), "ISO8859-1");// 谷歌
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + downloadFielName);
        ServletOutputStream output = null;  //定义了一个输出流，用于由Servlet向客户发送二进制数据

        BufferedInputStream br = null;
        try {
            br = new BufferedInputStream(new FileInputStream(file));

            byte[] buf = new byte[1024];
            int len = 0;
            output = response.getOutputStream();
            while ((len = br.read(buf)) > 0) {
                output.write(buf, 0, len);
            }
        } catch (Exception e) {
            log.error("文件下载异常", e);
            throw new Exception("文件下载错误，请联系管理员！");
        } finally {
            if (br != null) {
                br.close();
            }
            if (output != null) {
                output.close();
            }
        }
        return output;
    }
}
