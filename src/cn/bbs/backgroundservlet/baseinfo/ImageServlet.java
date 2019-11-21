package cn.bbs.backgroundservlet.baseinfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.sf.json.JSONObject;
@MultipartConfig
@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		 HashMap<String, Object> map=new HashMap<String, Object>();
			String path = req.getServletContext().getRealPath("/image/");
			System.out.println(path);
			Path file = Paths.get(path);
			if(!Files.exists(file)) {
				Files.createDirectory(file);
			}
			Part part=req.getPart("file");
			if(part.getSize()==0) {
				map.put("code",1);
		    	JSONObject.fromObject(map).write(resp.getWriter());
				return;

			}
			
			String name = part.getSubmittedFileName().trim();
			int index=name.lastIndexOf(".");
			String suffix="";
			if(index>0) {
				 suffix= name.substring(index);
			}
//			String savepath=path+UUID.randomUUID()+suffix;
			String savepath=path+"/"+"["+UUID.randomUUID().toString().substring(0,4)+"]"+name;
			
			
			System.out.println(savepath);
	        part.write(savepath);
	        String savepath2="http://"+req.getLocalAddr()+":"+req.getLocalPort()+req.getContextPath().replaceAll("\\\\", "/")+"/"+savepath.substring(savepath.indexOf("image"));
	        System.out.println(savepath2);
	        
	        System.out.println(savepath.substring(savepath.indexOf("image")));
	    	map.put("code", 0);
	    	map.put("img", savepath2);
	    	System.out.println(JSONObject.fromObject(map));
	    	JSONObject.fromObject(map).write(resp.getWriter());
		
	}

}
