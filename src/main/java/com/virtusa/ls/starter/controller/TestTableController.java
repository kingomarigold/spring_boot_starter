package com.virtusa.ls.starter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.virtusa.ls.starter.constants.Constants;
import com.virtusa.ls.starter.dto.TestTableDto;
import com.virtusa.ls.starter.service.testtable.TestTableService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Test Controller.
 *
 *
 * @author Karthik.
 */
@RestController
@RequestMapping("/" + Constants.BASEPATH + "/" + Constants.TEST_TABLE_PATH)
@Slf4j
public class TestTableController {

    @Autowired
    private TestTableService testTableService;

    /**
     * 
     * @return List<TestTableDto>
     */
    @GetMapping("")
    List<TestTableDto> all() {
        return this.testTableService.all();
    }

    @PostMapping("")
    ResponseEntity<?> save(@RequestBody @Valid TestTableDto testTableDto, Authentication authentication) {
        String name = authentication.getName();
        log.info("Username is: {}", name);
        TestTableDto retVal = this.testTableService.save(testTableDto);
        return retVal != null
                ? ResponseEntity
                        .created(UriComponentsBuilder.newInstance().pathSegment(Constants.BASEPATH)
                                .pathSegment(Constants.TEST_TABLE_PATH).pathSegment(retVal.getId()).build().toUri())
                        .build()
                : ResponseEntity.badRequest().build();
    }
    
    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable("id") String id) {
        TestTableDto retVal = this.testTableService.get(id);
        return retVal != null 
                ? ResponseEntity.ok(retVal)
                : ResponseEntity.notFound().build();
    }

}
