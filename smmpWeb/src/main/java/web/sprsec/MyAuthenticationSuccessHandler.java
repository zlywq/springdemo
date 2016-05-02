package web.sprsec;



import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import cmn2.util.*;
import spr2.service.*;

public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	
	@Autowired
	private UserInfoService userInfoService;
	
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
	        throws IOException, ServletException {
		logger.debug(""+this.getClass().getSimpleName()+"."+Util1.getMethodName()+" enter"+" RequestURI="+request.getRequestURI());
//		UtilMsg.getHttpServletRequestMsg(request);
		
//		/*
//ContextPath= /app1
//RequestURL= http://localhost:8080/app1/login
//RequestURI= /app1/login
//QueryString= null
//		 */
		
//		logger.debug("auth.getName()="+auth.getName());
		
//		HttpSessionSecurityContextRepository a;
		/*
这里是解决登录后不出session的cookie的问题。
    在log中会看到一个信息 WARN  HttpSessionSecurityContextRepository - Failed to create a session, as response has been committed. Unable to store SecurityContext
    这是在HttpSessionSecurityContextRepository中的createNewSessionIfAllowed函数中抛出的。
    大概原因是下面的 pw.write(jsonStr)写大量数据时，然后在那边调用request.getSession(true)就出错了。
    目前的办法是把 request.getSession(true) 的调用移到足够前面的位置。
		 */
		request.getSession(true);
		
		String needJson = request.getParameter("needJson");
		if ("true".equalsIgnoreCase(needJson)){
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control","no-cache");
			PrintWriter pw = response.getWriter();
			
			long userId = Long.parseLong(auth.getName());			
			Map<String, Object> mapUserData = userInfoService.getUserDataById(userId);
			
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.putAll(mapUserData);
			hm.put(Const.Key_success, true);
			hm.put(Const.Key_infoMsg, "登录成功");
			String jsonStr = JSONHelperSf.map2json(hm);//... check chinese character
			pw.write(jsonStr);
			return;
		}else{
			super.onAuthenticationSuccess(request, response, auth);
		}
	}
}
