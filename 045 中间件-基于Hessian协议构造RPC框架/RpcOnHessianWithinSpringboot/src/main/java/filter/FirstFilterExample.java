package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirstFilterExample {

	static class MyFilter implements Filter {

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {

		}

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			chain.doFilter(request, response);
		}

		@Override
		public void destroy() {

		}

	}

	@Bean
	// 函数名必须跟其它类的不相同
	public FilterRegistrationBean generateByFirstFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setOrder(0);// 顺序,值越小,越先执行
		List<String> urlList = new ArrayList<String>();// 针对这些url进行拦截
		urlList.add("/*");
		registrationBean.setUrlPatterns(urlList);
		registrationBean.setName("first filter");// Filter如下
		registrationBean.setFilter(new MyFilter());
		return registrationBean;
	}

}
