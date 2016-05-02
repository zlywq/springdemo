package web.sprsec;






import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;

import cmn2.util.*;

/*
 * 目前的配置导致未登录时访问需要登录才能访问的url时，会被提示登录的功能先拦截(被提示请登录)，而走不到这里来。倒是也没什么问题。
 */
public class AccessApiCheckFilter extends GenericFilterBean  {
	
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		 * 使用这个filter只能管是否服务准备好的这种情况。
		 * 如果想灵活的禁用、启用服务，不能在这个filter中做。因为启用服务必须能够调用api。需要在每个api的开始部分检查。
		 * 另外还不能对spring启动的其他模块施加影响，如消息队列
		 */
		HttpServletRequest hRequest = (HttpServletRequest)request;
		logger.debug(""+this.getClass().getSimpleName()+"."+Util1.getMethodName()+" enter"+" RequestURI="+hRequest.getRequestURI());

		chain.doFilter(request, response);
	}

}
