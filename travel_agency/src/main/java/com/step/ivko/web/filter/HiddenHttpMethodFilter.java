package com.step.ivko.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class HiddenHttpMethodFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String method = request.getParameter("_method");
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {
            @Override
            public String getMethod() {
                return method != null ? method.toUpperCase() : super.getMethod();
            }
        };
        chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {
    }
}