package client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.stereotype.Component;

import hessian.Hello;

@SuppressWarnings("deprecation")
@Component
public class HelloProxyClient {

    @Value("${server.port}")
    public int port;

    @Bean
    public HessianProxyFactoryBean helloClient() {
        HessianProxyFactoryBean clientProxy = new HessianProxyFactoryBean();
        clientProxy.setServiceUrl("http://localhost:" + port + "/hello-service-test");
        clientProxy.setServiceInterface(Hello.class);
        return clientProxy;
    }
}
