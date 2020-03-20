package com.github.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.spec.ProjectSpec;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractConverter {

    protected static final String SOURCE_PATH = "E:/repo/demo/";
    protected static final String TARGET_PATH = "E:/repo/demo1/";
    protected static final String SPEC_JSON_FILE = "E:/mygen/src/main/resources/model.json";
    protected final ProjectSpec projectSpec;
    private final Configuration configuration;
    protected final String packageSubPath;


    @SneakyThrows
    public AbstractConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        projectSpec = objectMapper.readValue(new File(SPEC_JSON_FILE), ProjectSpec.class);
        configuration = new Configuration(Configuration.getVersion());
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(SOURCE_PATH));
        configuration.setTemplateLoader(fileTemplateLoader);
        configuration.setDefaultEncoding("utf-8");
        String groupId = projectSpec.getGroupId();
        String[] split = groupId.split("\\.");
        String collect = String.join("/", split);
        packageSubPath = collect + "/" + projectSpec.getArtifactId();
    }

    @SneakyThrows
    public void transform(String fromFile, String toFile, Object dataModel) {
        Path dir = Paths.get(TARGET_PATH);
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        String fileName = TARGET_PATH + toFile;
        Path path = Paths.get(fileName);
        Path parent = path.getParent();
        if (!Files.exists(path)) {
            Files.createDirectories(parent);
        }
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        @Cleanup FileWriter writer = new FileWriter(fileName);
        Template template = configuration.getTemplate( fromFile);
        template.process(dataModel, writer);
    }

    @SneakyThrows
    public void transform(String fromFile, String toFile) {
        Path dir = Paths.get(TARGET_PATH);
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        String fileName = TARGET_PATH + toFile;
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Path path1 = Paths.get(SOURCE_PATH + fromFile);
        Files.copy(path1, path);
    }

    public abstract void convert();

}
