import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.model.DomainFileModel;
import com.github.spec.DomainSpec;
import com.github.spec.ProjectSpec;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private Configuration configuration;
    private ProjectSpec projectSpec;
    private String target;
    private Path mainSrcDir;
    private final String artifactId;

    Main() throws IOException {

        target = "D:\\repo\\08\\mygen\\src\\main\\resources\\target";
        configuration = new Configuration(Configuration.getVersion());
        configuration.setClassLoaderForTemplateLoading(Main.class.getClassLoader(), "/");
        configuration.setDefaultEncoding("utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        URL resource = Main.class.getResource("/model.json");
        projectSpec = objectMapper.readValue(resource, ProjectSpec.class);
        String join = String.join("\\", projectSpec.getGroupId().split("'.' 46"));
        artifactId = projectSpec.getArtifactId();
        mainSrcDir = Paths.get(target + "\\src\\main\\java\\"
                + join + "\\" + artifactId);
    }

    public void run() throws IOException, TemplateException {
        pom();
        gitignore();
        src();

    }

    private void src() throws IOException, TemplateException {

        Files.createDirectories(mainSrcDir);
        mainfile();
        List<DomainSpec> domains = projectSpec.getDomains();
        String domainDir = mainSrcDir + "/domain";
        Files.createDirectories(Paths.get(domainDir));
        for (DomainSpec domain : domains) {
            String name = domain.getName();
            String s = domainDir + "/" + StringUtils.capitalize(name) + ".java";
            @Cleanup FileWriter writer = new FileWriter(s);
            Template template = configuration.getTemplate("/ftp/Domain.java");
            DomainFileModel domainFileModel = new DomainFileModel(projectSpec, domain);
            template.process(domainFileModel, writer);
        }
    }

    private void mainfile() throws IOException, TemplateException {
        Path appFile = Paths.get(mainSrcDir.toString()+"\\" +
                StringUtils.capitalize(artifactId) + "Application.java");
        @Cleanup FileWriter writer = new FileWriter(appFile.toFile());
        Template template = configuration.getTemplate("/ftp/DemoApplication.java");
        template.process(projectSpec, writer);
    }

    private void gitignore() throws IOException, TemplateException {
        Template template = configuration.getTemplate("/ftp/.gitignore");
        @Cleanup Writer writer = new FileWriter(target + "\\.gitignore");
        template.process(projectSpec, writer);
    }

    private void pom() throws IOException, TemplateException {
        Template template = configuration.getTemplate("/ftp/pom.ftl");
        @Cleanup Writer writer = new FileWriter(target + "\\pom.xml");
        template.process(projectSpec, writer);
    }

    public static void main(String[] args) throws IOException, TemplateException {
        Main main = new Main();
        main.run();
    }
}
