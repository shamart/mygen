package com.github.model;

import com.github.spec.DomainField;
import com.github.spec.DomainSpec;
import com.github.spec.ProjectSpec;
import com.github.utils.Inflector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomainFileModel {
    private String groupId;
    private String artifactId;
    private String version;
    private String description;

    private Inflector pluralize=new Inflector();

    //domain
    private String domainName;
    private List<DomainField> domainProperties;

    public DomainFileModel(ProjectSpec projectSpec, DomainSpec domainSpec) {
        this.groupId = projectSpec.getGroupId();
        this.artifactId = projectSpec.getArtifactId();
        this.version = projectSpec.getVersion();
        this.description = projectSpec.getDescription();
        this.version = domainSpec.getName();
        this.domainName = domainSpec.getName();
        this.domainProperties = domainSpec.getProperties();
    }
}
