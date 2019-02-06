package com.learningspringboot.repository;

import com.learningspringboot.LearningSpringBootApplication;
import com.learningspringboot.db.TestEntity;
import com.learningspringboot.db.TestEntityRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= LearningSpringBootApplication.class)
@ActiveProfiles(profiles = "dev")
@Transactional
public class TestEntityRepositoryIntegrationTest {

    @Autowired
    private TestEntityRepository testEntityRepository;

    @Test
    public void testAddAnEntryIntoTable() {
        TestEntity testEntity = new TestEntity();
        testEntity.setCol1("SampleData");

        TestEntity t = testEntityRepository.save(testEntity);

        assertThat(t.getId()).isNotNull();
    }
}
