package com.github.converter;

import com.github.spec.DomainSpec;
import freemarker.template.TemplateException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SrcConverter extends AbstractConverter{

    private final Path mainSrcDir;
    private final String artifactId;

    public SrcConverter() {
        super();

        String join = String.join("/", projectSpec.getGroupId().split("'.' 46"));
        artifactId = projectSpec.getArtifactId();
        mainSrcDir = Paths.get(TARGET + "/src/main/java/"
                + join + "/" + artifactId);
    }

    @SneakyThrows
    @Override
    public void run() {
        Files.createDirectories(mainSrcDir);
        mainfile();

        List<DomainSpec> domains = projectSpec.getDomains();
        String domainDir = mainSrcDir + "/domain";
        String repositoryDir = mainSrcDir + "/repository";
        Files.createDirectories(Paths.get(domainDir));
        Files.createDirectories(Paths.get(repositoryDir));
        for (DomainSpec domain : domains) {
            domain(domainDir, domain);
            repository(repositoryDir, domain);
        }
    }
    private void mainfile() throws IOException, TemplateException {
        Path appFile = Paths.get(mainSrcDir.toString() + "/" +
                StringUtils.capitalize(artifactId) + "Application.java");
        transform("/ftp/DemoApplication.java", appFile.toString(), projectSpec);
    }

}
