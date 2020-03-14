package ${groupId}.${artifactId}.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@Entity
public class ${domainName} {
    @Id
    @GeneratedValue
    private Long id;
    <#list domainProperties as x>
    private ${x.type} ${x.value};
    </#list>
}
