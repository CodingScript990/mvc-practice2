package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Controller Interface -> 어디에 활용할 것인가? -> ElementTyp[Annotation]
// Controller 에 annotation 을 사용하기 위한 작업
// Retention : 유지기간은 어찌할 건가를 묻는 것임 -> RUNTIME 까지 유지!!
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    //
}
