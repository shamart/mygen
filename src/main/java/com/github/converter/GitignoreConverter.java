package com.github.converter;

public class GitignoreConverter extends AbstractConverter {

    @Override
    public void converte() {
        transform(".gitignore",".gitignore");
    }
}
