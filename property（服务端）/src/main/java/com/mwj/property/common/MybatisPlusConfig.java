package com.mwj.property.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//MyBatis-Plus和Spring框架中的类和注解，用于配置和实现分页拦截器。

@Configuration
//这个类被注解为@Configuration，表明它是一个配置类。MybatisPlusConfig类用于配置MyBatis-Plus的拦截器。
public class MybatisPlusConfig {

    @Bean
    //方法被注解为@Bean，Spring会将其返回的对象注册为Spring容器中的一个Bean。方法名为mybatisPlusInterceptor。
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
        //MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();：创建一个新的MyBatis-Plus拦截器实例。
        //interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));：添加一个分页拦截器，并指定数据库类型为MySQL。
        //return interceptor;：返回配置好的拦截器实例。
    }
}