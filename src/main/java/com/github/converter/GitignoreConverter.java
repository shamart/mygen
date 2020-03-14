package com.github.converter;

public class GitignoreConverter extends AbstractConverter {

    @Override
    String fromFile() {
        return "/ftp/.gitignore";
    }

    @Override
    String toFile() {
        return TARGET + "/.gitignore";
    }

    @Override
    Object dataModel() {
        return projectSpec;
    }
}
