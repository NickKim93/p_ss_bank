package com.bank.profile.util;

import com.bank.profile.entity.Audit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Slf4j
@EnableJpaRepositories
public class AuditingEntityListener {

    @PrePersist
    public void prePersist(Object entity) throws JsonProcessingException {
        if (entity instanceof Audit audit) {
            createStructure(audit, OperationType.CREATE);
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) throws JsonProcessingException {
        if (entity instanceof Audit audit) {
            createStructure(audit, OperationType.UPDATE);
        }
    }

    protected void createStructure(Audit audit, OperationType operationType) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        final String entityJson = objectMapper.writeValueAsString(audit);

        switch (operationType) {
            case CREATE -> {
                audit.setNewEntityJson(entityJson);
                audit.setCreatedBy("SYSTEM");
            }
            case UPDATE -> {
                if (audit.getNewEntityJson() != null) {
                    audit.setEntityJson(audit.getNewEntityJson());
                }

                audit.setNewEntityJson(entityJson);
                audit.setModifiedBy("SYSTEM");
            }
        }

        audit.setEntityType(audit.getClass().getSimpleName());
        audit.setOperationType(operationType.name());
        log.info("Запись успешно добавлена/изменена в таблице аудита");
    }
}
