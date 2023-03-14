package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerExample {
	static class MyListener implements ServletContextListener {

		@Override
		public void contextInitialized(ServletContextEvent sce) {

		}

		@Override
		public void contextDestroyed(ServletContextEvent sce) {

		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public ServletListenerRegistrationBean getExampleListener() {
		ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean<>();
		registrationBean.setListener(new MyListener());
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
