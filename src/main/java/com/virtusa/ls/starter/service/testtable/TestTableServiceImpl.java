package com.virtusa.ls.starter.service.testtable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.ls.starter.dto.TestTableDto;
import com.virtusa.ls.starter.entity.TestTable;
import com.virtusa.ls.starter.repository.TestTableRepository;

/**
 * 
 * Test Table Service Implementation.
 *
 *
 * @author Karthik.
 */
@Service
public class TestTableServiceImpl implements TestTableService {

    @Autowired
    private TestTableRepository testTableRepository;

    @Override
    public List<TestTableDto> all() {
        return this.testTableRepository.findAll().stream()
                .map(value -> new TestTableDto(value.getId().toString(), value.getName())).toList();
    }

    @Override
    public TestTableDto save(TestTableDto testTableDto) {
        TestTable testTable = new TestTable();
        testTable.setName(testTableDto.getName());
        testTable = this.testTableRepository.save(testTable);
        return new TestTableDto(testTable.getId().toString(), testTable.getName());
    }

    @Override
    public TestTableDto get(String id) {
        Optional<TestTable> testTable = testTableRepository.findById(UUID.fromString(id));
        return testTable.isPresent() ? new TestTableDto(testTable.get().getId().toString(), testTable.get().getName())
                : null;
    }

}
