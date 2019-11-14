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
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse =(HttpServletResponse)response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpResponse.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader("origin"));
//		httpResponse.setHeader("Access-Control-Allow-Headers", "Authentication");
		httpResponse.addHeader("Access-Control-Allow-Credentials","true");
		chain.doFilter(httpRequest, httpResponse);
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
