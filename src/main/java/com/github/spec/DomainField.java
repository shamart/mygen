package com.github.spec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomainField {
    private String type;
    private String value;
    private DomainFieldValidation validation;
}
