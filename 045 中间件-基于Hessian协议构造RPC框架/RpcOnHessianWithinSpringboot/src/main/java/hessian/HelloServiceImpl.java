package hessian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceImpl implements Hello {

	private static Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);

	@Override
	public String sayHello(String msg) {
		log.info("--------------进入hessian方法，客户端参数：" + msg + "------------------");
		return msg + " hessian";
	}

}