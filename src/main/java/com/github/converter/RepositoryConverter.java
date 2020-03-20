package com.github.converter;

import com.github.model.DomainFileModel;
import com.github.spec.DomainSpec;
import org.apache.commons.lang3.StringUtils;

public class RepositoryConverter extends AbstractConverter {
    @Override
    public void converte() {
        for (DomainSpec domain : projectSpec.getDomains()) {
            DomainFileModel domainFileModel = new DomainFileModel(projectSpec, domain);
            String from = "src/main/java/com/example/demo/repository/CoinRepository.ftl";
            String groupId = projectSpec.getGroupId();
            String[] split = groupId.split("\\.");
            String collect = String.join("/", split);

            String to = "src/main/java/" + collect + "/" + projectSpec.getArtifactId()
                    + "/repository/" + StringUtils.capitalize(domain.getName()) +
                    "Repository.java";
            transform(from, to, domainFileModel);
        }

    }
}
