package pol.ecom.micro.shop.lib.config;

/*
 *@Created 11/08/2022 - 13:28
 *@Author nguyenmanhha
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();

    }



}
