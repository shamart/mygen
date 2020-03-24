package com.github.spec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomainField {
    private String type;
    private String value;
    private List<DomainFieldValidation> validations;
}
