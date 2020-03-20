package com.github.converter;

import com.github.model.DomainFileModel;
import com.github.spec.DomainSpec;
import org.apache.commons.lang3.StringUtils;

public class ServiceConverter extends AbstractConverter {
    @Override
    public void convert() {
        for (DomainSpec domain : projectSpec.getDomains()) {
            DomainFileModel domainFileModel = new DomainFileModel(projectSpec, domain);
            String from = "src/main/java/com/example/demo/service/CoinService.ftl";
            String groupId = projectSpec.getGroupId();
            String[] split = groupId.split("\\.");
            String collect = String.join("/", split);

            String to = "src/main/java/" + packageSubPath
                    + "/service/" + StringUtils.capitalize(domain.getName()) +
                    "Service.java";
            transform(from, to, domainFileModel);
        }
    }
}
