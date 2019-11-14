package cn.bbs.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 编码格式问题
 * @author Hydra
 *
 */
@WebFilter("/EncodingFilter")
public class EncodingFilter implements Filter {

	//定义变量接收初始化的值
    private static String encoding;
	
	public void destroy() {
		System.out.println("编码过滤器结束");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding("text/html;charset="+this.encoding);
		chain.doFilter(request, response);
	}


 
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("编码过滤器开始");
		encoding = fConfig.getInitParameter("CharsetEncoding");
		System.out.println(encoding);
	}

}
