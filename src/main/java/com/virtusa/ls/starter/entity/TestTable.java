package com.virtusa.ls.starter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * Entity for test table.
 *
 *
 * @author Karthik.
 */
@Entity
@Table(name="test_table")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class TestTable extends AuditableEntity {
    
    @Column(name="name")
    private String name;
}
