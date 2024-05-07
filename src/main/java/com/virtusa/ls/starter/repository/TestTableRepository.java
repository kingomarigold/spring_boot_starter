package com.virtusa.ls.starter.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.ls.starter.entity.TestTable;

@Repository
public interface TestTableRepository extends JpaRepository<TestTable, UUID> {
    // Add methods as needed.
}
