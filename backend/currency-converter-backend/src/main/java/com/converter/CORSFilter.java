package com.converter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*"); // allow all origins
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
