package com.kaishengit.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author NativeBoy
 */
public class RequestQuery {

    /**
     * 参数名称
     */
    private String parameterName;
    /**
     * 比较类型
     */
    private String type;
    /**
     * 参数值
     */
    private Object value;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static List<RequestQuery> getRequestQueryFromURL(HttpServletRequest request){
        List<RequestQuery> requestQueryList = new ArrayList<>();
        //获取所有查询参数的键值
        Enumeration<String> enumeration =  request.getParameterNames();
        while (enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            String value = request.getParameter(key);
            if(key.startsWith("q_") && value != null && !"".equals(value)){
                String[] array = key.split("_");
                if(array == null || array.length != 4){
                    throw new IllegalArgumentException("请求参数异常" + key);
                }
                RequestQuery requestQuery = new RequestQuery();
                requestQuery.setParameterName(array[1]);
                requestQuery.setValue(tranValueType(array[3],value));
                requestQuery.setType(array[2]);

                requestQueryList.add(requestQuery);
            }
        }
        return requestQueryList;
    }

    private static Object tranValueType(String valueType, String value){
        if("i".equalsIgnoreCase(valueType)){
            return Integer.valueOf(value);
        }else if("s".equalsIgnoreCase(valueType)){
            return value;
        }else if("d".equalsIgnoreCase(valueType)){
            return Double.valueOf(value);
        }else if("f".equalsIgnoreCase(valueType)){
            return Float.valueOf(value);
        }else if ("bd".equalsIgnoreCase(valueType)){
            return new BigDecimal(value);
        }
        return null;
    }
}
