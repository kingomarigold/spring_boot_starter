package com.virtusa.ls.starter.entity;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @CreatedDate
    @Column(name="created_date")
    private Instant createdDate;
    
    @LastModifiedDate 
    @Column(name="last_modified_date")
    private Instant lastModifiedDate;
    
    @CreatedBy
    @Column(name="created_by")
    private UUID createdBy;
    
    @LastModifiedBy
    @Column(name="last_modified_by")
    private UUID lastModifiedBy;
    
}
