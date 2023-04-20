package com.bank.antifraud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * Класс, описывающий сущность Audit
 *
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
    private Long id;
    @Column(name = "entity_type",
            nullable = false)
    private String entityType;
    @Column(name = "operation_type",
            nullable = false)
    private String operationType;
    @Column(name = "created_by",
            nullable = false)
    private String createdBy;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "created_at",
            nullable = false)
    private OffsetDateTime createdAt;
    @Column(name = "modified_at")
    private OffsetDateTime modifiedAt;
    @Column(name = "new_entity_json")
    private String newEntityJson;
    @Column(name = "entity_json",
            nullable = false)
    private String entityJson;
}
