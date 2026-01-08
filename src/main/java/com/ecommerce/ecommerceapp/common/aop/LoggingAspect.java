package com.ecommerce.ecommerceapp.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.ecommerce.ecommerceapp.modules..*)")
    public void applicationPackagePointcut() {
        // Pointcut for all classes in the application package
    }

    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        logger.info("Entering method: {} with arguments: {}", 
                   joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                   joinPoint.getArgs());

        try {
            Object result = joinPoint.proceed();
            
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            logger.info("Exiting method: {} with result: {}. Execution time: {} ms", 
                       joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                       result, duration);
            
            if (duration > 1000) { // Log if execution takes more than 1 second
                logger.warn("Method {} took {} ms to execute", 
                           joinPoint.getSignature().getName(), duration);
            }
            
            return result;
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            logger.error("Exception in method: {} after {} ms. Exception: {}", 
                        joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                        duration, e.getMessage());
            throw e;
        }
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in method: {} with cause: {}", 
                    joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), 
                    ex.getCause() != null ? ex.getCause() : "Unknown");
    }
}