package com.github.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.spec.ProjectSpec;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractConverter {

    protected static final String TARGET_PATH = "D:/repo/08/mygen/src/main/resources/target";
    protected static final String SOURCE_PATH = "D:/repo/08/mygen/src/main/resources/target/";
    protected static final String SPEC_JSON_FILE = "/model.json";
    protected final ProjectSpec projectSpec;
    private final Configuration configuration;

    @SneakyThrows
    public AbstractConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        URL resource = AbstractConverter.class.getResource(SPEC_JSON_FILE);
        projectSpec = objectMapper.readValue(resource, ProjectSpec.class);
        configuration = new Configuration(Configuration.getVersion());
        configuration.setDefaultEncoding("utf-8");
    }

    @SneakyThrows
    public void transform(String fromFile, String toFile, Object dataModel) {
        String fileName = TARGET_PATH + toFile;
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        @Cleanup FileWriter writer = new FileWriter(fileName);
        Template template = configuration.getTemplate(SOURCE_PATH + fromFile);
        template.process(dataModel, writer);
    }

    public abstract void converte();

}
