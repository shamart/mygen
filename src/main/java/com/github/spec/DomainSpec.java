package com.github.spec;

import com.github.spec.DomainField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomainSpec {
    private String name;
    private List<DomainField> properties;
}