package com.bank.antifraud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
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
