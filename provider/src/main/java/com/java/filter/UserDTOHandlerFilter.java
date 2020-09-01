package com.java.filter;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-08-20 09:55
 */

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import java.io.IOException;
import java.net.URLDecoder;

public class UserDTOHandlerFilter implements Filter {

    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userDTO = servletRequest.getParameter("userDTO");
        boolean notBlank = StringUtils.isNotBlank(userDTO);
        if (notBlank) {
            userDTO = URLDecoder.decode(userDTO, "utf-8");
            ThreadLocalUserDTOUtil.set(userDTO);
        }

        filterChain.doFilter(servletRequest, servletResponse);
        if (notBlank) {
            ThreadLocalUserDTOUtil.del();
        }

    }

    public void destroy() {
    }
}
