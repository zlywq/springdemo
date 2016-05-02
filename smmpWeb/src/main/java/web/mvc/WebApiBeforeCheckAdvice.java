package web.mvc;



import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import cmn2.util.Util1;



/*
 * 目前只是处理所有Controller的方法。
 * 与AccessApiCheckFilter相比，能够处理的web动作少一点。
 *     比如login的动作就处理不到，因为这个动作是在spring security体系中处理的。而不是在Controller的方法中。
 */
public class WebApiBeforeCheckAdvice implements MethodBeforeAdvice{

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
//	SettingService settingService;
//	public void setSettingService(SettingService settingService){
//		this.settingService = settingService;
//	}
	
	
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		logger.debug(""+this.getClass().getSimpleName()+"."+Util1.getMethodName()+" enter, method="+method+", args="+args+", target=+"+target);
		/*
method=public java.lang.String aaa.controller.LoginRegisterController.getLogin(boolean,org.springframework.ui.ModelMap), args=[Ljava.lang.Object;@3e352336, target=+aaa.controller.LoginRegisterController@129ace22
method=public java.lang.String aaa.controller.LoginRegisterController.infoLogReg(org.springframework.ui.Model), args=[Ljava.lang.Object;@53422065, target=+aaa.controller.LoginRegisterController@129ace22
method=public java.lang.String aaa.controller.UserController.getUser(org.springframework.ui.ModelMap,long,boolean), args=[Ljava.lang.Object;@5f4cf3cf, target=+aaa.controller.UserController@351acbea
		 */
//		if (target instanceof UserController){
//			logger.debug("target instanceof UserController");
//		}
		
	}

	
}
