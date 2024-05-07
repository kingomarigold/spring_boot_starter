package com.virtusa.ls.starter.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * Dto for test table.
 *
 *
 * @author Karthik
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestTableDto {
    private String id;
    
    @NotBlank
    private String name;
}
