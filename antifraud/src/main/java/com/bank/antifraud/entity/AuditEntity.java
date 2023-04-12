package com.bank.antifraud.entity;

import com.bank.antifraud.util.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * Класс, описывающий сущность Audit
 * @author Makariy Petrov
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditEntity {
    @Column(name = "entity_type",
            nullable = false,
            table = "audit")
    @JsonIgnore
    String entityType;
    @Column(name = "operation_type",
            nullable = false,
            table = "audit")
    @JsonIgnore
    String operationType;
    @Column(name = "created_by",
            nullable = false,
            table = "audit")
    @JsonIgnore
    String createdBy;
    @Column(name = "modified_by",
            table = "audit")
    @JsonIgnore
    String modifiedBy;
    @Column(name = "created_at",
            nullable = false,
            table = "audit")
    @JsonIgnore
    Timestamp createdAt;
    @Column(name = "modified_at",
            table = "audit")
    @JsonIgnore
    Timestamp modifiedAt;
    @Column(name = "new_entity_json",
            table = "audit")
    @JsonIgnore
    String newEntityJson;
    @Column(name = "entity_json",
            nullable = false,
            table = "audit")
    @JsonIgnore
    String entityJson;
}
