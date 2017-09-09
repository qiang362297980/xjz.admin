package com.tool.upload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.secondBack.tools.XJZConstant;
import com.tool.basic.TemplateConfig;

/**
 * Created by XCA on 2017/5/23.
 */
public class UploadServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(UploadServlet.class);
	
    public static void doPost(MultipartHttpServletRequest request, HttpServletResponse response) {
    	
    	
    	String savePath = TemplateConfig.getValue(XJZConstant.IMAGESAVEPATH);
        
        if(StringUtils.isEmpty(savePath)){
        	savePath = "";
        }
        
		log.error("imageSavePath:"+savePath);
        
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }

        List<MultipartFile> files = request.getFiles("fileData");
        
        String url = "";
        String pathname = null;
        String currentTime = null;
        String sixRandomNum = null;
        String fileSuffix = "*.jpg";
        
        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyyMMddHHmmssSSS");

        try {
			for (MultipartFile file : files) {
				
				if(!StringUtils.isEmpty(file.getOriginalFilename()) && file.getOriginalFilename().lastIndexOf(".") >= 0){
					fileSuffix = file.getOriginalFilename()
							.substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());
				} 
				
				currentTime = dateFormat.format(new Date());
				sixRandomNum = String.valueOf(new Double((Math.random()*9+1) *100000).intValue());
				pathname = savePath + currentTime + sixRandomNum + fileSuffix;
				file.transferTo(new File(pathname));
				url =url + TemplateConfig.getValue(XJZConstant.IMAGESAVEURL)+ currentTime + sixRandomNum + fileSuffix + ",";
			}
			
			request.setAttribute("url", url);
			 	 
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
        }
       
    }
 
 
	public static void doGet(MultipartHttpServletRequest request,HttpServletResponse response){
        doPost(request, response);
    }
}
