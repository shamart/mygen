package com.github.converter;

public class PomConverter extends AbstractConverter {


    @Override
    public void convert() {
        transform("pom.ftl","pom.xml",projectSpec);
    }
}
