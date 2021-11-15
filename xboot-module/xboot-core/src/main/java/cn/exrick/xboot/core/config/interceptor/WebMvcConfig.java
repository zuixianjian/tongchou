package cn.exrick.xboot.core.config.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${xboot.file.win}")
    private String winFilePath;
    @Value("${xboot.file.linux}")
    private String linuxFilePath;
    // 实现静态资源的映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/files/**")
                    .addResourceLocations("file:" + getFilePath());  // 映射本地静态资源
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public String getFilePath(){
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")){
            File file = new File(winFilePath);
            if (!file.exists()) {
                file.mkdirs();
            }
           return winFilePath;
        }else{
            File file = new File(linuxFilePath);
            if (!file.exists()) {
                file.mkdirs();
            }
           return linuxFilePath;
        }
    }
}