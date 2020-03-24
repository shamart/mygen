package com.github.spec;

import com.github.utils.Class2FinalField;
import lombok.Data;

import java.util.List;

@Data
public class ProjectSpec {
    private String groupId;
    private String artifactId;
    private String version;
    private String description;
    private List<DomainSpec> domains;
    private Class2FinalField class2FinalField = new Class2FinalField();

}
