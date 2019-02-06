package com.learningspringboot.db;

import javax.persistence.*;

@Entity
@Table(name = "TEST")
public class TestEntity {
    @Id
    @SequenceGenerator(sequenceName = "seq_sample", name = "seq_sample", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sample")
    private Integer id;
    private String col1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }
}
