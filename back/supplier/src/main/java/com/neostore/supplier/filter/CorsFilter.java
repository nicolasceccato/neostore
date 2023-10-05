package com.neostore.supplier.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Allow requests from any origin
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");

        // Allow certain HTTP methods
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");

        // Allow specific headers
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Allow credentials (if needed)
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        // Continue with the request chain
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Not needed for this example
    }

    @Override
    public void destroy() {
        // Not needed for this example
    }
}
