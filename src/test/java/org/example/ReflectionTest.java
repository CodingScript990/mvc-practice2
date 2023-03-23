package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

// ReflectionTest Class -> TDD
public class ReflectionTest {
    /**
     * [조건]
     * - @Controller Annotation 이 설정되 있는 모든 Class 를 찾아서 출력함
     */
    // Logger add -> ReflectionTest Class 가 잘 동작하는가를 확인하기 위한 작업
    public static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    // ControllerScan method add
    @Test
    void controllerScan() {
        /*
        // 1. 리팩토링 전!
        // Reflections 를 활용하여 package 를 불러옴 -> org.example 기준으로 모든 부분을 활용한다는 의미임
        Reflections reflections = new Reflections("org.example");

        // Set Class Type add
        Set<Class<?>> beans = new HashSet<>();
        // Set Class 를 이용하여 org.example package 에 모든 곳에 추가할 건데, reflections Class 에서 getTypesAnnotateWith method 에서 Controller Annotation 을 찾아온다는 의미
        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));
        // Service 추가까지 확인 가능!
        beans.addAll(reflections.getTypesAnnotatedWith(Service.class));
         */

        // 2. 리팩토링 후
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        // Logger 를 통해서 잘동작 하는지 Output 으로 Check
        logger.debug("beans: [{}]", beans);
    }
    
    // showClass method add -> User class[call]
    @Test
    void showClass() {
        // Class Generic Type -> User Class 사용하는데, name : clazz 이고 User.class code 를 사용하겠다는 의미
        Class<User> clazz = User.class;
        // debug 를 통해서 clazz 의 data type 및 사용된 import 를 확인하기 위한 작업 -> logger 로 표시
        logger.debug(clazz.getName());
        // debug[fields] 를 통해서 Arrays 안에 stream method 를 통해 clazz 의 멤버변수에 접근하겠다는 의미이고, collect method 는 stream data 를 변형등 처리를 하고 원하는 자료형(User)으로 변환해주기 위한 것
        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        // debug[constructor] 를 통해서 Arrays 안에 stream method 를 통해 clazz 의 멤버변수에 접근하겠다는 의미이고, collect method 는 stream data 를 변형등 처리를 하고 원하는 자료형(User)으로 변환해주기 위한 것
        logger.debug("User all declared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        // debug[method] 를 통해서 Arrays 안에 stream method 를 통해 clazz 의 멤버변수에 접근하겠다는 의미이고, collect method 는 stream data 를 변형등 처리를 하고 원하는 자료형(User)으로 변환해주기 위한 것
        logger.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }
    
    // 힙영역의 Load 되어 있는 Class Type Object 를 가져오는 method add
    @Test
    void load() throws ClassNotFoundException {
        // 힙영역의 Load 되어있는 Class Type 을 들고오는 방법 1
        // 1. Class Generic Type -> User Class 사용하는데, name : clazz 이고 User.class code 를 사용하겠다는 의미
        Class<User> clazz = User.class;

        // 힙영역의 Load 되어있는 Class Type 을 들고오는 방법 2
        // 2. Instance Class 를 직접적으로 call 하는 방법
        User user = new User("kim", "김김김");
        Class<? extends User> clazz2 = user.getClass();

        // 힙영역의 Load 되어있는 Class Type 을 들고오는 방법 3
        // Package 를 직접 Class 에서 들고오는 방법
        Class<?> clazz3 = Class.forName("org.example.model.User");

        // 3가지 방법들이 동일한 Object 들인지 Test 작업 -> logger 로 output call
        // 힙영역의 Load 되어있는 Class Type 을 들고오는 방법 1
        logger.debug("clazz: [{}]", clazz);
        // 힙영역의 Load 되어있는 Class Type 을 들고오는 방법 2
        logger.debug("clazz2: [{}]", clazz2);
        // 힙영역의 Load 되어있는 Class Type 을 들고오는 방법 3
        logger.debug("clazz3: [{}]", clazz3);

        // 3가지 방법들이 동일한 Object 들인지 Test 작업 -> AssertThat method call
        // clazz 와 clazz2 비교
        assertThat(clazz == clazz2).isTrue();
        // clazz2 와 clazz3 비교
        assertThat(clazz2 == clazz3).isTrue();
        // clazz3 와 clazz 비교
        assertThat(clazz3 == clazz).isTrue();
    }

    // 인자값 받아오는 작업 -> [List Type] -> Controller, Service 받아옴
    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example");

        // Set Class Type add
        Set<Class<?>> beans = new HashSet<>();

        // forEach method 를 활용하여 reflections Class 에서 getTypesAnnotateWith method 에서 Controller Annotation, Service Annotation 을 찾아온다는 의미
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));
        // return beans
        return beans;
    }
}
