package com.mcmd.common.aop;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.mcmd.common.constant.CommonConstant;
import com.mcmd.common.event.ExceptionRecordEvent;
import com.mcmd.common.pojo.AutopilotExceptionRecord;
import com.mcmd.common.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ppliu
 * @version 1.0
 * @date 2022/3/23 10:30
 */
@Slf4j
@Aspect
@Component
public class ExceptionRecordAspect {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Pointcut("@annotation(com.mcmd.common.aop.ExceptionRecordAop)")
    public void pointcut() {

    }

    @AfterThrowing(pointcut = "pointcut()&&@annotation(aop)", throwing = "e")
    public void recordException(JoinPoint joinPoint, Exception e, ExceptionRecordAop aop) {
        log.info("异常抓取并存档 ...");
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();
        List<String> clazzList = new ArrayList<>();
        List<Map> dataList = new ArrayList<>();
        if(ObjectUtil.isNotEmpty(args)) {
            for (Object arg : args) {
                clazzList.add(arg.getClass().getName());
                dataList.add(JacksonUtil.string2Obj(JacksonUtil.obj2String(arg), Map.class));
            }
        }
        String message = e.getMessage();
        AutopilotExceptionRecord record = new AutopilotExceptionRecord();
        record.setOperationType(aop.type());
        record.setOperationFlag(CommonConstant.OPERATION_STATUS_UNDO);
        record.setExceptionInfo(e.toString());
        record.setExceptionClass(e.getClass().getName());
        record.setExceptionMessage(message);
        record.setProjectModel(className);
        record.setProjectMethod(methodName);
        record.setOperationData(dataList.toString());
        record.setOperationDataClass(clazzList.toString());
        record.setExceptionEngenderDate(DateUtil.format(new Date(), "yyyy-MM-dd"));
        ExceptionRecordEvent event = new ExceptionRecordEvent(record);
        publisher.publishEvent(event);
        log.info("异常信息为：{}", message);
    }

}
