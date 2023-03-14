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
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondFilterExample {

	static class MyFilter implements Filter {

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {

		}

		@SuppressWarnings("unused")
		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			HttpServletRequest httpReq = (HttpServletRequest) request;
			// 比如说,如果没有登录
			// if (1==2&&null == httpReq.getHeader("token")) {
			// HttpServletResponse httpResponse = (HttpServletResponse)
			// response;
			// httpResponse.setCharacterEncoding("UTF-8");
			// httpResponse.setContentType("application/json; charset=utf-8");
			// httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//
			// 基于状态码来表示
			// httpResponse.getWriter().write("{\"succeed\":0,\"msg\":\"please
			// login firstly!\"}");
			// return;
			// }
			chain.doFilter(request, response);
		}

		@Override
		public void destroy() {

		}

	}

	@Bean
	public FilterRegistrationBean generateBySecondFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setOrder(1);// 顺序,值越小,越先执行
		List<String> urlList = new ArrayList<String>();// 针对这些url进行拦截
		urlList.add("/*");
		registrationBean.setUrlPatterns(urlList);
		registrationBean.setName("second filter");// Filter如下
		registrationBean.setFilter(new MyFilter());
		return registrationBean;
	}

}
