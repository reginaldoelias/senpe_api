package br.mil.mar.saudenaval.senpe.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;

@Configuration
public class ProxyConfig {

    @Bean
    public RestTemplate restTemplate(){
        String proxyHost = "http://proxy-1dn.mb";
        int proxyPort = 6060;
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));

        // Configuração do request factory com proxy
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);

        // Adicionando autenticação ao cabeçalho
            ClientHttpRequestInterceptor authInterceptor = (request, body, execution) -> {
            String auth = "07349386:Lotus@notes123";
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
            request.getHeaders().add("Proxy-Authorization", "Basic " + encodedAuth);
            return execution.execute(request, body);
        };

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setInterceptors(Collections.singletonList(authInterceptor));
        return restTemplate;
    }
}
