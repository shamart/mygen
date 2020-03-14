package com.example.demo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
}
