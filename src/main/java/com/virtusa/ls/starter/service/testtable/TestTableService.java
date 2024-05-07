package com.virtusa.ls.starter.service.testtable;

import java.util.List;

import com.virtusa.ls.starter.dto.TestTableDto;

/**
 * 
 * Interface for test service.
 *
 *
 * @author Karthik.
 */
public interface TestTableService {
    /**
     * 
     * @return List<TestTableDto>
     */
    List<TestTableDto> all();
    
    /**
     * 
     * @param TestTableDto.
     * @return TestTableDto - will return null on failure.
     */
    TestTableDto save(TestTableDto testTableDto);
    
    /**
     * 
     * @param id String.
     * @return TestTableDto
     */
    TestTableDto get(String id);
}
