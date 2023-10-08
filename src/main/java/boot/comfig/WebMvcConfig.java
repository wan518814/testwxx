package boot.comfig;

import boot.http.message.PropertiesPersonHttpMessageConverter;
//import org.springframework.cloud.commons.httpclient.HttpClientConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
//        converters.add(new MappingJackson2CborHttpMessageConverter());
//        System.err.println("converters:"+converters);
//    }

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(new PropertiesPersonHttpMessageConverter());
        System.err.println("converters:"+converters);

    }
}
