package com.mwj.property.common;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.CorsRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//这个类被注解为@Configuration，表明它是一个配置类。
// CorsConfig实现了WebMvcConfigurer接口，该接口提供了自定义Spring MVC配置的方法。
public class CorsConfig implements WebMvcConfigurer {
    @Override
    //这个方法是WebMvcConfigurer接口的一部分，用于添加CORS映射。
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放⾏哪些原始域
                .allowedOriginPatterns("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");
        //配置了CORS（跨域资源共享），允许来自不同域名的请求访问springboot应用
    }
}