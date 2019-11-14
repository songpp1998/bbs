package cn.bbs.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Hydra
 *
 *跨域问题
 */
@WebFilter("/CrossFilter")
public class CrossFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse Response =(HttpServletResponse)response;
		HttpServletRequest Request = (HttpServletRequest) request;
		//动态获取域
		Response.setHeader("Access-Control-Allow-Origin",Request.getHeader("origin"));
		//允许携带cookie
		Response.addHeader("Access-Control-Allow-Credentials","true");
		chain.doFilter(Request, Response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
