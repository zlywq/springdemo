package web.mvc;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import cmn2.util.Util1;


//@Component("springStartFinishListener") //使用这个不好使，暂且使用xml配置了
public class SpringStartFinishListener  implements ApplicationListener<ContextRefreshedEvent> {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
//	@Autowired
//	Global global;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//会被调用两次，注意代码要能防止重入
		logger.info(""+this.getClass().getSimpleName()+"."+Util1.getMethodName()+" enter");
//		global.initAll();	
	}

}
