package com.github.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.spec.ProjectSpec;
import com.github.utils.FtlConfUtil;
import freemarker.template.Template;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileWriter;
import java.net.URL;

public abstract class AbstractConverter implements Runnable {

    protected static final String TARGET = "D:/repo/08/mygen/src/main/resources/target";
    protected static final String MODEL = "/model.json";
    protected final ProjectSpec projectSpec;

    @SneakyThrows
    public AbstractConverter()  {
        ObjectMapper objectMapper = new ObjectMapper();
        URL resource = AbstractConverter.class.getResource(MODEL);
        projectSpec = objectMapper.readValue(resource, ProjectSpec.class);
    }

    @SneakyThrows
    @Override
    public void run() {
        @Cleanup FileWriter writer = new FileWriter(toFile());
        Template template = FtlConfUtil.getConf().getTemplate(fromFile());
        template.process(dataModel(), writer);
    }

    abstract String toFile();

    abstract String fromFile();

    abstract Object dataModel();
}