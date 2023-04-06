package com.bank.profile.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Entity
@Table(name = "audit")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "entity_type")
    @NotNull
    @NotBlank
    @Size(max = 40)
    private String entityType;
    @Column(name = "operation_type")
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String operationType;
    @Column(name = "created_by")
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String createdBy;
    @Column(name = "modified_by")
    @Size(max = 255)
    private String modifiedBy;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @NotNull
    @NotBlank
    private OffsetDateTime createdAt;
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime modifiedAt;
    @Column(name = "new_entity_json")
    private String newEntityJson;
    @Column(name = "entity_json")
    @NotNull
    @NotBlank
    private String entityJson;
}
