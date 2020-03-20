package com.github.converter;

public class ApplicationPropertiesConverter extends AbstractConverter {
    @Override
    public void convert() {
        transform("src/main/resources/application.ftl",
                "src/main/resources/application.properties", projectSpec);
    }
}
