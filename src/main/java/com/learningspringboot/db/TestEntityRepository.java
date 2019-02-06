package com.learningspringboot.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityRepository extends  JpaRepository<TestEntity, Integer> {

}
