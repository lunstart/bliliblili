package com.bliliblili.domain.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/16 20:16
 * @ 注释 自定义接口 管理接口权限
 */

/**
 * 该注解用于指定其所修饰的注解在运行时可被Java反射机制读取。
 * 这意味着在程序运行期间可以获取到被此注解标注的注释，并对其进行处理。
 *
 * @Retention 注解用来定义注解的生命周期
 * @param value 指定注解的生命周期为运行时（RetentionPolicy.RUNTIME）
 */
@Retention(RetentionPolicy.RUNTIME)

/**
 * 该注解用于指定其应用目标的元素类型，典型地应用在方法上。
 * @Target注解指定了这个注解可以用于什么地方。在这个例子中，它被限定用于方法。
 *
 * @param ElementType.METHOD 指定注解的应用目标为方法。
 */
@Target({ElementType.METHOD})

/**
 * 该注解用于指定其他注解是否应该被包含在Javadoc中。
 * 当注解了@Documented的自定义注解被应用到类、方法或字段上时，
 * 这些自定义注解将会成为API文档的一部分。
 */
@Documented


@Component

public @interface ApiLimitedRole {
    String[] limitedRoleCodeList() default {};
}
