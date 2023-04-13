package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.OffsetDateTime;

/**
 * Класс, описывающий сущность Audit
 * @author Savenkov Artem
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
    private BigInteger id;
    @Column(name = "entity_type", nullable = false)
    @Size(max = 40)
    private String entityType;
    @Column(name = "operation_type", nullable = false)
    @Size(max = 255)
    private String operationType;
    @Column(name = "created_by", nullable = false)
    @Size(max = 255)
    private String createdBy;
    @Column(name = "modified_by")
    @Size(max = 255)
    private String modifiedBy;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    private OffsetDateTime createdAt;
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    private OffsetDateTime modifiedAt;
    @Column(name = "new_entity_json")
    private String newEntityJson;
    @Column(name = "entity_json", nullable = false)
    private String entityJson;

}
