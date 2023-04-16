package com.bank.profile.entity;

import com.bank.profile.util.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;


import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * Сущность аудита системы
 * */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
public class Audit {
    @Column(name = "entity_type", table = "audit")
    @JsonIgnore
    protected String entityType;
    @Column(name = "operation_type", table = "audit")
    @JsonIgnore
    protected String operationType;
    @Column(name = "created_by", table = "audit")
    @CreatedBy
    @JsonIgnore
    protected String createdBy;
    @Column(name = "modified_by", table = "audit")
    @LastModifiedBy
    @JsonIgnore
    protected String modifiedBy;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", table = "audit")
    @CreationTimestamp
    @JsonIgnore
    protected OffsetDateTime createdAt;
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", table = "audit")
    @UpdateTimestamp
    @JsonIgnore
    protected OffsetDateTime modifiedAt;
    @Column(name = "new_entity_json", table = "audit")
    @JsonIgnore
    protected String newEntityJson;
    @Column(name = "entity_json", table = "audit")
    @JsonIgnore
    protected String entityJson;
}
