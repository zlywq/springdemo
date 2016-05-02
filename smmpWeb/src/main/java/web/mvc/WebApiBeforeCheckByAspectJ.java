package web.mvc;


import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmn2.util.*;


/*
 * 目前只是处理所有Controller的方法。
 * 与AccessApiCheckFilter相比，能够处理的web动作少一点。
 *     比如login的动作就处理不到，因为这个动作是在spring security体系中处理的。而不是在Controller的方法中。
 */
public class WebApiBeforeCheckByAspectJ {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
//	SettingService settingService;
//	public void setSettingService(SettingService settingService){
//		this.settingService = settingService;
//	}
	
	public void beforeMethod(JoinPoint joinPoint) {
		//如果需要这里可以取出参数进行处理
		logger.debug(""+this.getClass().getSimpleName()+"."+Util1.getMethodName()+" enter, joinPoint="+joinPoint+". details:Target="+joinPoint.getTarget()+", Class="+joinPoint.getClass()+", Kind="+joinPoint.getKind()+", Args="+joinPoint.getArgs());

		/*
joinPoint=execution(String aaa.controller.LoginRegisterController.getLogin(boolean,ModelMap)). details:Target=aaa.controller.LoginRegisterController@50ffcd2b, Class=class org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint, Kind=method-execution, Args=[Ljava.lang.Object;@49f6eb98
joinPoint=execution(String aaa.controller.InfoController.getCommonPage(Model)). details:Target=aaa.controller.InfoController@7b139a31, Class=class org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint, Kind=method-execution, Args=[Ljava.lang.Object;@43f04617
joinPoint=execution(String aaa.controller.UserController.getUser(ModelMap,long,boolean)). details:Target=aaa.controller.UserController@5a00231a, Class=class org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint, Kind=method-execution, Args=[Ljava.lang.Object;@3f00ae1f
joinPoint=execution(String aaa.controller.AccountController.info(ModelMap)). details:Target=aaa.controller.AccountController@47343f54, Class=class org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint, Kind=method-execution, Args=[Ljava.lang.Object;@3729c851
		 */
		
		String sJoinPoint = joinPoint.toString();
		if (sJoinPoint.contains(".controller.LoginRegisterController.getLogin")){
			//特意在login页面不做检查，以使管理员能够登录
		}else{
			//注意这个检查在AccessApiCheckFilter那边也有配合
			//当IsDisableForSysMaintain标识被设置为true时，普通用户不能做任何操作，但系统用户可以。
//			if (settingService.getCommonConfig_IsDisableForSysMaintain()){
//				long userIdInSession = Util1.getUserIdInSession(false);
//				if (!Util1.isSysUser(userIdInSession)){
//					DemoBaseException.throwEx2(ErrCode.Bus_Common,"系统维护中，服务不可用。");
//				}
//			}
		}
		
		
		
	}

}
