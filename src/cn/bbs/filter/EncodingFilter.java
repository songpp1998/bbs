package cn.bbs.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class EncodingFilter implements Filter {

protected String encoding=null; 
	
	class MyRequestWrapper extends HttpServletRequestWrapper{

		public MyRequestWrapper(HttpServletRequest request) {
			super(request);
		}
		@Override
		public String getParameter(String name) {
			 String value=null;
	            try{
	                //post提交
	                super.setCharacterEncoding(encoding);
	                value=super.getParameter(name);
	                if(super.getMethod().equalsIgnoreCase("GET"))//get提交
	                {
	                    if(value!=null)
	                    {
	                        value=new String(value.getBytes("iso8859-1"), encoding);
	                    }
	                }        
	            }
	            catch(UnsupportedEncodingException e){
	                e.printStackTrace();
	            }        
	            return value;
		}
	}
	
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		encoding = arg0.getInitParameter("encoding");
		
	}
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//避免响应出现乱码情况
		arg1.setCharacterEncoding("text/html;charset="+this.encoding);
        //把过滤器给下一个过滤器或者资源处理器
		System.out.println("text/html;charset="+this.encoding);
		arg2.doFilter(new MyRequestWrapper((HttpServletRequest)arg0), arg1);
		
	}

	@Override
	public void destroy() {
		encoding = null;
		
	}
}
