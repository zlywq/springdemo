package p1t;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cmn1t.Tool1;
import spr1t.Service1;

@Controller
@RequestMapping("/c1")
public class Controller1 {
	
	@Autowired 
	Service1 service1;
	

	@RequestMapping(value = "/info")
	public String info(ModelMap model) {				
		System.out.println("Controller1.info enter");

		
		Tool1.f1();

		service1.f1api();
		

		return "c1/info";
	}
	
}