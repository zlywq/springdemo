package p1t;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spr1t.Service1;
import cmn1t.Tool1;



public class Run1 {

	public static void main(String[] args) {
		System.out.println("Run1.main enter");
		Tool1.f1();

		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/root-context.xml");
		Service1 svc1 = ctx.getBean(Service1.class);
		svc1.f1api();


	}

}
