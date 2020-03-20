package com.github.converter;

public class PomConverter extends AbstractConverter {


    @Override
    public void converte() {
        transform("pom.ftl","pom.xml",projectSpec);
    }
}
