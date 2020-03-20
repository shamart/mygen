package com.github.converter;

import com.github.model.DomainFileModel;
import com.github.spec.DomainSpec;

public class DTOConverter extends AbstractConverter {
    @Override
    public void convert() {
        for (DomainSpec domain : projectSpec.getDomains()) {
            DomainFileModel domainFileModel = new DomainFileModel(projectSpec, domain);
            coinCreateDTO(domainFileModel);
            coinFindDTO(domainFileModel);
            coinUpdateDTO(domainFileModel);
        }
    }
    private void coinUpdateDTO(DomainFileModel dataModel) {
        String domainName = dataModel.getDomainName();
        String fromFile = "src/main/java/com/example/demo/dto/CoinUpdateDTO.ftl";
        transform(fromFile, "src/main/java/" + packageSubPath
                + "/dto/"+domainName+"UpdateDTO.java", dataModel);
    }
    private void coinFindDTO(DomainFileModel dataModel) {
        String domainName = dataModel.getDomainName();
        String fromFile = "src/main/java/com/example/demo/dto/CoinFindDTO.ftl";
        transform(fromFile, "src/main/java/" + packageSubPath
                + "/dto/"+domainName+"FindDTO.java", dataModel);
    }

    private void coinCreateDTO(DomainFileModel dataModel) {
        String domainName = dataModel.getDomainName();
        String fromFile = "src/main/java/com/example/demo/dto/CoinCreateDTO.ftl";
        transform(fromFile, "src/main/java/" + packageSubPath
                + "/dto/"+domainName+"CreateDTO.java", dataModel);
    }
}
