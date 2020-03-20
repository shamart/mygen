package com.github.converter;

public class GitignoreConverter extends AbstractConverter {

    @Override
    public void convert() {
        transform(".gitignore",".gitignore");
    }
}
