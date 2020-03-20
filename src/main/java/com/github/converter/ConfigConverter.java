package com.github.converter;

public class ConfigConverter extends AbstractConverter {
    @Override
    public void convert() {
        String fromFile = "src/main/java/com/example/demo/config/Swagger2Config.ftl";
        transform(fromFile, "src/main/java/" + packageSubPath
                + "/config/Swagger2Config.java", projectSpec);
    }
}
