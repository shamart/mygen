package com.github.converter;

public class ApplicationPropertiesConverter extends AbstractConverter {
    @Override
    public void converte() {
        transform("src/main/resources/application.ftl",
                "src/main/resources/application.properties", projectSpec);
    }
}
