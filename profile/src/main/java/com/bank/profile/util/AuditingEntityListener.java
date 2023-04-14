package com.bank.profile.util;

import com.bank.profile.entity.Audit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Slf4j
@EnableJpaRepositories
public class AuditingEntityListener {


    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof Audit audit) {
            createStructure(audit, OperationType.CREATE);
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        if (entity instanceof Audit audit) {
            createStructure(audit, OperationType.UPDATE);
        }
    }

    private void createStructure(Audit audit, OperationType operationType) {
        ObjectMapper objectMapper = new ObjectMapper();
        String entityJson = null;

        try {
            entityJson = objectMapper.writeValueAsString(audit);
        } catch (JsonProcessingException e) {
            audit.setNewEntityJson("entityJson");
            log.error("Ошибка преобразования Entity в JSON для записи в таблицу аудита");
        }

        switch (operationType) {
            case CREATE :
                audit.setEntityJson(entityJson);
                audit.setCreatedBy("SYSTEM");
                break;

            case UPDATE :
                if (audit.getNewEntityJson() != null)
                    audit.setEntityJson(audit.getNewEntityJson());
                audit.setNewEntityJson(entityJson);
                audit.setModifiedBy("SYSTEM");
        }

        audit.setEntityType(audit.getClass().getSimpleName());
        audit.setOperationType(operationType.name());
        log.info("Запись успешно доавлена/изменена в таблице аудита");
    }
}
