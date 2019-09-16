/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.people.restservice.RESTservice.aspect;

import com.people.restservice.RESTservice.services.PlayerServiceImpl;
import java.time.LocalDateTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author reta_
 */
@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Pointcut("execution(* com.people.restservice.RESTservice.services.*.*(..))")
    public void serviceMethod() { }
    
    //@Before("execution(* com.people.restservice.RESTservice.services.PlayerService.create(..))")
    @Before("serviceMethod()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("logBefore() is Running!\n"+" \tProcess intervened: " +  joinPoint.getSignature().getName()+"\n ******");
    }
    
    //@After("execution(* com.people.restservice.RESTservice.services.PlayerService.create(..))")
    @After("serviceMethod()")
    public void logAfter() {
        log.info("logAfter() is finished! " +LocalDateTime.now()+"\n");
    }   
}