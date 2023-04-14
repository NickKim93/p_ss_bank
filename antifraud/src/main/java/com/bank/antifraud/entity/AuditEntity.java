package com.bank.antifraud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Класс, описывающий сущность Audit
 * @author Makariy Petrov
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit")
public class AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "entity_type",
            nullable = false)
    String entityType;
    @Column(name = "operation_type",
            nullable = false)
    String operationType;
    @Column(name = "created_by",
            nullable = false)
    String createdBy;
    @Column(name = "modified_by")
    String modifiedBy;
    @Column(name = "created_at",
            nullable = false)
    Timestamp createdAt;
    @Column(name = "modified_at")
    Timestamp modifiedAt;
    @Column(name = "new_entity_json")
    String newEntityJson;
    @Column(name = "entity_json",
            nullable = false)
    String entityJson;
}
