package com.example.restApi.aspect;

import com.example.restApi.dto.RequestUserDto;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class UserAspect {


    @Pointcut("within(com.example.restApi.controller.UserController)")
    public void controllerPointCut() {
    }

    @Pointcut("within(com.example.restApi.service.UserServiceImpl)")
    public void servicePointCut() {
    }

   /* @Pointcut("args(name)")
    public void argsPointCut(String name) {
    }

    @Around("argsPointCut(name)")
    public void nameAdvice(String name)
    {
        log.info("Id has been affected");
    }*/

    @After("execution(public * findById(..))")
    public void afteradvice()
    {
        log.info("After advice called");
    }

  //  @Before(value = "execution(* com.example.restApi.service..*.*(..))")
    @Before("controllerPointCut() || servicePointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        log.info(joinPoint.getSignature().getDeclaringType().getSimpleName() + " @" + joinPoint.getSignature().getName() + " -- START");
        //log.info("name is=>"+codeSignature.getParameterNames()[0]);
    }
}
