package web.mvc;



import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmn2.util.Util1;


/*
 * 目前只是处理所有Controller的方法。
 * 与AccessApiCheckFilter相比，能够处理的web动作少一点。
 *     比如login的动作就处理不到，因为这个动作是在spring security体系中处理的。而不是在Controller的方法中。
 */
public class WebApiAfterLogByAspectJ {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
//	SettingService settingService;
//	public void setSettingService(SettingService settingService){
//		this.settingService = settingService;
//	}
	
	public void afterMethod(JoinPoint joinPoint) {
		logger.debug(""+this.getClass().getSimpleName()+"."+Util1.getMethodName()+" enter, joinPoint="+joinPoint+". details:Target="+joinPoint.getTarget()+", Class="+joinPoint.getClass()+", Kind="+joinPoint.getKind()+", Args="+joinPoint.getArgs());
	}

}
