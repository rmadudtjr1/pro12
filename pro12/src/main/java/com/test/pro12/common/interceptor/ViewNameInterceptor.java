package com.test.pro12.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *  문제. ViewNameInterceptor 를 통해서
 * 	request 안에 viewName 을 넣고 
 *  interceptor 가 Servlet 시작 전에 구현 되도록 해보세요.
 *
 */

public class ViewNameInterceptor 
	extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			String viewName = getViewName(request);
			request.setAttribute("viewName", viewName);
			System.out.println("viewName : " + viewName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private String getViewName(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute
				("javax.servlet.include.request_uri");

		if(uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;

		if(!(contextPath == null)|| !("".equals(contextPath))) {
			begin = contextPath.length();
		}

		int end = 0;
		if(uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}
		int i = 0;
		String viewName = uri.substring(begin, end);
		System.out.println(i++ + " : " + viewName);
		if(viewName.indexOf(".") != -1) {
			viewName = viewName.substring
					(0, viewName.lastIndexOf("."));
		}
		System.out.println(i++ + " : " + viewName);
		if(viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring
					(viewName.lastIndexOf("/", 1), viewName.length());
		}
		System.out.println(i++ + " : " + viewName);
		return viewName;

	}

}

