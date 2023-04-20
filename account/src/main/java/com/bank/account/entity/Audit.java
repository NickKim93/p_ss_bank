package com.bank.account.entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Table(schema = "account",
        name = "audit")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "entity_type",
            nullable = false,
            length = 40,
            table = "audit")
    @NotNull
    @Size(max = 40)
    private String entityType;
    @Column(name = "operation_type",
            nullable = false,
            table = "audit")
    @NotNull
    private String operationType;
    @Column(name = "created_by",
            nullable = false,
            table = "audit")
    @NotNull
    private String createdBy;
    @Column(name = "modified_by",
            table = "audit")
    private String modifiedBy;
    @Column(name = "created_at",
            nullable = false,
            table = "audit")
    @NotNull
    private Timestamp createdAt;
    @Column(name = "modified_at",
            table = "audit")
    private Timestamp modifiedAt;
    @Column(name = "new_entity_json",
            table = "audit")
    private String newEntityJson;
    @Column(name = "entity_json",
            table = "audit",
            nullable = false)
    @NotNull
    private String entityJson;
}

