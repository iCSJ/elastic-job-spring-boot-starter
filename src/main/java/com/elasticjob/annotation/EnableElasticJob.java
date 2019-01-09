package com.elasticjob.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import com.elasticjob.autoconfigure.JobParserAutoConfiguration;

/**
 * @author chengshijun@haiermoney.com
 * @version V1.0.0
 * @date 2018/12/14 10:37
 * @description EnableElasticJob ElasticJob 开启注解,在Spring Boot 启动类上加此注解开启自动配置
 * @Copyright 2018 www.haiermoney.com Inc. All rights reserved.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({JobParserAutoConfiguration.class})
public @interface EnableElasticJob {

}