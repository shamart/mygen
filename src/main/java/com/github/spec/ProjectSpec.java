package com.github.spec;

import com.github.spec.DomainSpec;
import lombok.Data;

import java.util.List;

@Data
public class ProjectSpec {
    private String groupId;
    private String artifactId;
    private String version;
    private String description;
    private List<DomainSpec> domains;

}
