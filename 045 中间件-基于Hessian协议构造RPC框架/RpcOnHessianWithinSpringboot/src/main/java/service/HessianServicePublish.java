package service;

import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;

import hessian.Hello;
import hessian.HelloServiceImpl;

@SuppressWarnings("deprecation")
@Component
public class HessianServicePublish {

	@Bean(name = "/hello-service-test")
	public HessianServiceExporter servicePublish() {
		//
		HessianServiceExporter exporter = new HessianServiceExporter();
		//
		exporter.setServiceInterface(Hello.class);
		exporter.setService(new HelloServiceImpl());
		//
		return exporter;
	}
}
