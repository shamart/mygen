package com.github.converter;

import org.apache.commons.lang3.StringUtils;

public class SpringApplicationConverter extends AbstractConverter {
    @Override
    public void convert() {
        String from = "src/main/java/com/example/demo/DemoApplication.ftl";
        String to = "src/main/java/" + packageSubPath +"/"+
                StringUtils.capitalize(projectSpec.getArtifactId()) + "Application.java";
        transform(from, to, projectSpec);
    }
}
