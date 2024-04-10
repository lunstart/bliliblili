package com.bliliblili.service.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JsonHttpMessageConverterConfig {
    @Bean
    @Primary
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 创建FastJsonHttpMessageConverter实例
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 创建FastJsonConfig实例，用于配置FastJson的行为
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        // 设置日期格式为"yyyy-MM-dd HH:mm:ss"
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        // 设置序列化特性
        fastJsonConfig.setSerializerFeatures(
                // 格式化输出JSON字符串，使其更易读
                SerializerFeature.PrettyFormat,
                // 将null字符串序列化为空字符串
                SerializerFeature.WriteNullStringAsEmpty,
                // 将null集合序列化为空集合
                SerializerFeature.WriteNullListAsEmpty,
                // 序列化Map中的null值
                SerializerFeature.WriteMapNullValue,
                // 对Map按键进行排序输出
                SerializerFeature.MapSortField,
                // 禁止循环引用检测
                SerializerFeature.DisableCircularReferenceDetect);

        // 将配置好的FastJsonConfig对象设置到FastJsonHttpMessageConverter中
        fastConverter.setFastJsonConfig(fastJsonConfig);

        // 创建并返回一个包含fastConverter的HttpMessageConverters对象
        return new HttpMessageConverters(fastConverter);
    }
}
