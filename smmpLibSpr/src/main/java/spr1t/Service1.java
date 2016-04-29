package spr1t;

import org.springframework.stereotype.Service;

import cmn1t.Tool1;



@Service
public class Service1 {

	
	public void f1api(){
		System.out.println("Service1.f1api");
		Tool1.f1();
		
	}
}