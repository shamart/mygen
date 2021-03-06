package com.github.converter;

import com.github.model.DomainFileModel;
import com.github.spec.DomainSpec;
import org.apache.commons.lang3.StringUtils;

public class DomainConverter extends AbstractConverter {
    @Override
    public void convert() {
        for (DomainSpec domain : projectSpec.getDomains()) {
            DomainFileModel domainFileModel = new DomainFileModel(projectSpec, domain);
            String from = "src/main/java/com/example/demo/domain/Coin.ftl";
            String groupId = projectSpec.getGroupId();
            String[] split = groupId.split("\\.");
            String collect = String.join("/", split);

            String to = "src/main/java/" + packageSubPath + "/domain/" + StringUtils.capitalize(domain.getName()) + ".java";
            transform(from, to, domainFileModel);
        }

    }
}
