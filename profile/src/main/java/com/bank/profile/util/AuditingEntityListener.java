package com.bank.profile.util;

import com.bank.profile.entity.Audit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Slf4j
@EnableJpaRepositories
@RequiredArgsConstructor
public class AuditingEntityListener {

    private final ObjectMapper objectMapper;

    @PrePersist
    public void prePersist(Object entity) throws JsonProcessingException {
        if (entity instanceof Audit audit) {
            createStructure(audit, OperationType.CREATE);
            log.info("Запись успешно добавлена в таблицу аудита");
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) throws JsonProcessingException {
        if (entity instanceof Audit audit) {
            createStructure(audit, OperationType.UPDATE);
            log.info("Запись успешно изменена в таблице аудита");
        }
    }

    protected Audit createStructure(Audit audit, OperationType operationType) throws JsonProcessingException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        final String entityJson;
        try {
            entityJson = objectMapper.writeValueAsString(audit);
        } catch (JsonProcessingException exception) {
            log.error("Ошибка преобразования Entity в JSON для записи в таблицу аудита []");
            throw new JsonProcessingException(exception) {
            };
        }

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
        return audit;
    }
}
