package com.github.converter;

public class PomConverter extends AbstractConverter {

    @Override
    String toFile() {
        return TARGET + "/pom.xml";
    }

    @Override
    String fromFile() {
        return "/ftp/pom.ftl";
    }

    @Override
    Object dataModel() {
        return projectSpec;
    }
}
