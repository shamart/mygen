package com.github.converter;

import com.github.model.DomainFileModel;
import com.github.spec.DomainSpec;
import org.apache.commons.lang3.StringUtils;

public class DomainConverter extends AbstractConverter {
    @Override
    public void converte() {
        for (DomainSpec domain : projectSpec.getDomains()) {
            DomainFileModel domainFileModel = new DomainFileModel(projectSpec, domain);
            String from = "src/main/java/com/example/demo/domain/User.ftl";
            String groupId = projectSpec.getGroupId();
            String[] split = groupId.split("\\.");
            String collect = String.join("/", split);

            String to = "src/main/java/" + collect + "/" + projectSpec.getArtifactId() + "/domain/" + StringUtils.capitalize(domain.getName()) + ".java";
            transform(from, to, domainFileModel);
        }

    }
}
