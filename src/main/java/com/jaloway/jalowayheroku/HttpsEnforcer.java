package com.jaloway.jalowayheroku;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpsEnforcer implements Filter {
	
	public static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		System.out.println("HttpsEnforcer Starting");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		System.out.println("request.getServerName(): " + request.getServerName());
		
		if (request.getHeader(X_FORWARDED_PROTO) != null) {
			System.out.println("request.getHeader(X_FORWARDED_PROTO) != null: " + request.getHeader(X_FORWARDED_PROTO));
			if (request.getHeader(X_FORWARDED_PROTO).indexOf("https") != 0) {
				System.out.println("request.getHeader(X_FORWARDED_PROTO).indexOf(\"https\") != 0: " + request.getHeader(X_FORWARDED_PROTO).indexOf("https"));
				String pathInfo = (request.getPathInfo() != null) ? request.getPathInfo() : "";
				System.out.println("pathInfo: " + pathInfo);
				response.sendRedirect("https://" + request.getServerName() + pathInfo);
				System.out.println("\"https://\" + request.getServerName() + pathInfo: " + "https://" + request.getServerName() + pathInfo);
				return;
			}
		}
		
		filterChain.doFilter(request, response);

	}
	
	@Override
	public void destroy() {}

}
