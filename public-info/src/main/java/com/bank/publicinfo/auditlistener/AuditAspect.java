package com.bank.publicinfo.auditlistener;

import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.repository.AuditRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    private final AuditRepository auditRepository;

    private final ObjectMapper objectMapper;

    public AuditAspect(AuditRepository auditRepository, ObjectMapper objectMapper) {
        this.auditRepository = auditRepository;
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @AfterReturning(pointcut = "@annotation(Auditable)", returning = "returnValue")
    public void audit(JoinPoint joinPoint, Object returnValue) throws JsonProcessingException {
        String operationType = null;
        Object[] args = joinPoint.getArgs();

        if (joinPoint.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            if (signature.getMethod().isAnnotationPresent(Auditable.class)) {
                Auditable auditable = signature.getMethod().getAnnotation(Auditable.class);
                operationType = auditable.operationType();
            }
        }
        if (operationType != null) {
            String entityType = args[0].getClass().getSimpleName();
            String entityJson = objectMapper.writeValueAsString(args[0]);

            String newEntityJson = null;
            if (args.length > 1) {
                newEntityJson = objectMapper.writeValueAsString(args[1]);
            }
            String createdBy = "some username";
            String modifiedBy = "some username";
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime createdAt = null;
            LocalDateTime modifiedAt = null;
            if (operationType.equals("create") || operationType.equals("delete")) {
                createdAt = now;
            } else if (operationType.equals("update")) {
                createdAt = now;
                modifiedAt = now;
            }
            Audit audit = new Audit(entityType, operationType, createdBy, modifiedBy, createdAt, modifiedAt, newEntityJson, entityJson);
            auditRepository.save(audit);
        }
    }
}

