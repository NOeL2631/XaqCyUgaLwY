// 代码生成时间: 2025-10-04 03:43:19
package com.example.api;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * API响应格式化工具，用于生成格式化的API响应
 */
public class ApiResponseFormatter {

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";

    /**
     * 生成成功的API响应
     *
     * @param data 返回的数据对象
     * @return 格式化的API响应
     */
    public static String success(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, "success");
        response.put(MESSAGE, "Operation completed successfully");
        response.put(DATA, data);
        return formatResponse(response);
    }

    /**
     * 生成失败的API响应
     *
     * @param message 错误消息
     * @return 格式化的API响应
     */
    public static String failure(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, "error");
        response.put(MESSAGE, message);
        return formatResponse(response);
    }

    /**
     * 格式化响应数据
     *
     * @param response 响应数据
     * @return 格式化的字符串
     */
    private static String formatResponse(Map<String, Object> response) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            // 设置Content-Type为application/json
            request.setContentType("application/json");
            // 使用Jackson库将响应数据转换为JSON格式
            // 此处省略Jackson库的使用代码
            // 假设使用Jackson库将response转换为JSON字符串
            String jsonResponse = "{"status":"success","message":"Operation completed successfully","data":{}}";
            return jsonResponse;
        } catch (IOException e) {
            // 错误处理
            return failure("Error occurred while formatting response");
        }
    }
}
