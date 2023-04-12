package com.bank.antifraud.entity;

import com.bank.antifraud.util.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
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
@SecondaryTable(name = "audit")
public class AuditEntity {
    /**
     * Уникальный id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger id;
    @Column(name = "entity_type",
            nullable = false,
            table = "audit")
    String entityType;
    @Column(name = "operation_type",
            nullable = false,
            table = "audit")
    String operationType;
    @Column(name = "created_by",
            nullable = false,
            table = "audit")
    String createdBy;
    @Column(name = "modified_by",
            table = "audit")
    String modifiedBy;
    @Column(name = "created_at",
            nullable = false,
            table = "audit")
    Timestamp createdAt;
    @Column(name = "modified_at",
            table = "audit")
    Timestamp modifiedAt;
    @Column(name = "new_entity_json",
            table = "audit")
    String newEntityJson;
    @Column(name = "entity_json",
            nullable = false,
            table = "audit")
    String entityJson;
}
