package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// RequestMapping Interface -> Annotation
// Target 설정 -> Type(Class), Method
// Retention : 유지기간은 어찌할 건가를 묻는 것임 -> RUNTIME 까지 유지!!
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    // value 의 값이 없다면 빈 문자열인 variable 를 default 로 생성
    String value() default "";
    // RequestMethod 의 값이 없다면 빈 Method 를 default 로 생성
    RequestMethod[] method() default {};
}
