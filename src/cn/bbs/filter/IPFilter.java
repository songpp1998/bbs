package cn.bbs.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


/**
 * 过滤IP的过滤器
 * @author Hydra
 *
 */
@WebFilter("/BanIpFilter")
public class IPFilter implements Filter {
	
	//要过滤的ip
	private List<String> denyList = new ArrayList<String>();
	
	
	public void destroy() {
		System.out.println("过滤器IpFilter结束。");
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//获取访问的IP地址
		String remoteAddr = request.getRemoteAddr();
		
		response.setContentType("text/html;charset=utf-8");
		
		if(denyList.size()==0||denyList==null) {
			chain.doFilter(request, response);
		}else {
			Boolean flag = true;//true为不限制访问
			
			for (String regex : denyList) {
				if(remoteAddr.matches(regex)) {
					//ip被限制
					flag = false;
					break;
				}
			}
			if(flag) {
				chain.doFilter(request, response);
			}else {
				response.getWriter().write("ip被禁止");
				System.out.println("被禁止");
			}
		}
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		try {
			System.out.println("过滤器IpFilter开始初始化，功能：IP访问限制");
			initConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	

	}
	
	public void initConfig() throws IOException {
		
		
		//将文件转化成流
		InputStream inputStream = IPFilter.class.getResourceAsStream("/ipconfig.properties");
		
		Properties properties = new Properties();

		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//获取禁止ip的字符串
		String denyIP = properties.getProperty("denyIP");
		
		if(!validate(denyIP)) {
			throw new RuntimeException("配置文件有错，请检查！");
		}
		
		//读取单个ip
		if(null != denyIP && !"".equals(denyIP.trim())) {
			String[] denyIPs = denyIP.split(",|;");
			for(String ip : denyIPs) {
				denyList.add(ip);
			}
		}
		
		//打印所有黑名单ip
		for(String str : denyList) {
			System.out.println(str);
		}
	}
	
	public Boolean validate(String denyIP) {
		Boolean result = false;
		//IP地址每一段的正则
		String regx = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		//整个ip的正则
		String ipRegx = regx + "\\." + regx + "\\."+ regx + "\\." + regx;
		
		//对第一种方式进行校验
		Pattern pattern = Pattern.compile("("+ipRegx+")|("+ipRegx+"(,|;))*");
		if(this.isNullorMatches(denyIP, pattern)){
			result = true;  //匹配成功
		} else {
			result = false;
		}
		
		return result;
	}
	public Boolean isNullorMatches(String deny, Pattern pattern) {
		//如果为空，说明用户没添加该项，不做处理
		if(deny == null || "".equals(deny.trim())) {
			return true;
		} else {
			//在最后面没有,或;的给添上
			if(!deny.endsWith(";") && !deny.endsWith(",")) {
				deny += ";";
			}
			//如果匹配，则返回true
			if(pattern.matcher(deny).matches()) {
				return true;
			}
		}
		
		return false;
	}


}
