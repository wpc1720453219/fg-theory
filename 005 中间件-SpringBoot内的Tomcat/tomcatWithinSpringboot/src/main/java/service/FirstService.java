package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// 固定使用Service注解,正常情况下使用默认的单例模式
// 正常情况下非单例的可以用BeanContextHelper.getBean(类名)来获取,看自己的需求.
@Service
// @Scope("prototype")---自己 根据实际情况决定使用单例还是原型
public class FirstService {

	//@Value("${service.url}")
	private String url="";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public FirstService() {
		System.out.println("first service created ...--->" + this);
	}
}
