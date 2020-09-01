package com.java.filter;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-08-20 09:52
 */
public class ThreadLocalUserDTOUtil {
    private static final Logger log = LoggerFactory.getLogger(ThreadLocalUserDTOUtil.class);
    private static final ThreadLocal threadLocal = new ThreadLocal();

    public ThreadLocalUserDTOUtil() {
    }

    public static void del() {
        threadLocal.remove();
    }

    public static void set(Object object) {
        threadLocal.set(object);
    }

    public static Object get(Class clazz) {
        String userDTOStr = (String)threadLocal.get();
        return StringUtils.isNotBlank(userDTOStr) ? JSONObject.parseObject(userDTOStr, clazz) : null;
    }
}
