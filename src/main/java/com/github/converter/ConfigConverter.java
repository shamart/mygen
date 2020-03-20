package com.github.converter;

public class ConfigConverter extends AbstractConverter {
    @Override
    public void convert() {
        swagger();
        bean();

    }

    private void bean() {
        String fromFile = "src/main/java/com/example/demo/config/BeanConfig.ftl";
        transform(fromFile, "src/main/java/" + packageSubPath
                + "/config/BeanConfig.java", projectSpec);
    }

    private void swagger() {
        String fromFile = "src/main/java/com/example/demo/config/Swagger2Config.ftl";
        transform(fromFile, "src/main/java/" + packageSubPath
                + "/config/Swagger2Config.java", projectSpec);
    }
}
