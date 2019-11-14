package cn.bbs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.apache.commons.collections.map.HashedMap;

import cn.bbs.filter.IPFilter;
import cn.bbs.message.Message;

/**
 * 操作禁止ip的工具类
 * @author DELL
 *
 */
public class DenyIPService {
	
	private List<String> denyList = new ArrayList<String>();

	/**
	 * 获取禁止ip列表
	 * @return
	 * @throws IOException
	 */
	public Message getIPList() throws IOException {
		
		Properties props = new Properties();
		//加载配置文件
		InputStream file = DenyIPService.class.getResourceAsStream("/ipconfig.properties");
		props.load(file);
		
		String denyIP = props.getProperty("denyIP");
		
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
	
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", denyList);
		
		return new Message(true, 200, "查询成功",map );
	}
	
	
	public Message addDenyIp(String path,String dIP) throws IOException {
		
		Properties props = new Properties();		
//		System.out.println(path);

		String p = path.split(".metadata")[0];
		
		String realPath = p+ "bbs\\\\src\\\\ipconfig.properties";
//		System.out.println(p);
//		path = "E:\\exclipse项目\\bbs系统\\bbs\\src\\ipconfig.properties";
		
//		try {
//			oFile = new FileOutputStream(path,true);//true表示追加打开
//		}catch (Exception e) {
//			System.out.println("error...........");
//		}
////		new DenyIPService().getIPList();
////		props.setProperty("denyIP", dIP+";");
////		System.out.println(props.getProperty("denyIP"));
		
		try {
			if(!validate(dIP)) {
				return new Message(false, 300, "ip格式错误，请检查", null);
			}else {
//				props.store(oFile, null);
//				System.out.println(denyIP+";");
//				oFile.flush();
//				oFile.close();
				
				Writer out =new FileWriter(realPath,true);
				//写入文件
				out.write(dIP+";");
				out.flush();
				out.close();
				return new Message(true, 200, "ip已添加", null);
			}
		} catch (Exception e) {
			System.out.println("更新错误");
			return null;
		}
		
	}
	
	//将禁止ip放开
	public Message allowIP(String path,String aIP) throws IOException {
		
		Properties props = new Properties();
		//加载配置文件
		InputStream file = DenyIPService.class.getResourceAsStream("/ipconfig.properties");
		props.load(file);
		
		String denyIP = props.getProperty("denyIP");
		
		if(!validate(denyIP)) {
			return new Message(false, 300, "配置文件出错，请检查", null);
		}
		String content = "denyIP=";
		
		//读取单个ip
		if(null != denyIP && !"".equals(denyIP.trim())) {
			String[] denyIPs = denyIP.split(",|;");
			for(String ip : denyIPs) {
				if(!aIP.equals(ip)) {
					content += ip+";";
				}
				System.out.println(content);
			}
		}
		
		String p = path.split(".metadata")[0];
		
		String realPath = p+ "bbs\\\\src\\\\ipconfig.properties";
		Writer out =new FileWriter(realPath);
		//写入文件
		out.write(content);
		out.flush();
		out.close();
		
		return  new Message(true, 200, "ip已删除", null);
		
		//打印所有黑名单ip
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
