package com.github.converter;

import com.github.model.DomainFileModel;
import com.github.spec.DomainSpec;
import org.apache.commons.lang3.StringUtils;

public class ControllerConverter extends AbstractConverter {
    @Override
    public void convert() {

        for (DomainSpec domain : projectSpec.getDomains()) {
            DomainFileModel domainFileModel = new DomainFileModel(projectSpec, domain);
            String from = "src/main/java/com/example/demo/controller/CoinController.ftl";

            String to = "src/main/java/" + packageSubPath + "/controller/"
                    + StringUtils.capitalize(domain.getName()) + "Controller.java";
            transform(from, to, domainFileModel);
        }
    }
}
