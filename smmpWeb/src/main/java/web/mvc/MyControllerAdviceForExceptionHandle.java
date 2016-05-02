package web.mvc;



import java.util.*;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import cmn2.util.*;

@ControllerAdvice
public class MyControllerAdviceForExceptionHandle {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	


	

	/*
可以捕获参数输入格式错误。但不能捕获404的没有对于某url的处理函数错。
如org.springframework.beans.TypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: "123123123123123123"
可以通过.json返回json格式数据，而不必设置@ResponseBody来强制json返回。参考http://www.tuicool.com/articles/beQJRre

后来通过跟踪 org.springframework.web.servlet.DispatcherServlet 的源码发现，
    如果把 DispatcherServlet的throwExceptionIfNoHandlerFound属性设置为true，通过web.xml中相关配置。
    则可以捕获到导致错误信息为PageNotFound - No mapping found for HTTP request with URI的实际为 No handler found for GET的错误

注意如果是WxpayController中直接返回xml内容的处理函数。不能把Error放到这里来处理。不然会导致其他错误，如
Could not resolve view with name 'errInfo' in servlet with name 'appServlet'
使用了 MyXmlView 支持xml，还是不行。 
	 */
	@ExceptionHandler(Exception.class) //使用 @ExceptionHandler(Throwable.class) 并不能捕获更多错误，比如404的。
	public ModelAndView handleError(Exception error, HttpServletRequest request, HttpServletResponse response) {
		logger.debug(""+this.getClass().getSimpleName()+"."+Util1.getMethodName()+" enter, err got:"+error+" RequestURI="+request.getRequestURI());//在retriveErrMsgAndCodeToMap_withLog还会记log

//		DispatcherServlet a;
//		org.springframework.web.servlet.view.json.MappingJackson2JsonView j;
//		org.springframework.web.servlet.view.xml.MarshallingView xml;
		
		ModelAndView modelAndView = new ModelAndView("errInfo");//这里使用一个指定的err的page，避免使用默认的错误页面，因为默认的错误页面信息往往很不准确。
		UtilMsg.retriveErrMsgAndCodeToMap_withLog(error, modelAndView.getModel(), request.getRequestURL(), request.getQueryString());
		
		return modelAndView;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
