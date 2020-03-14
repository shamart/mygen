import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Arrays;

public class Main {

    private Configuration configuration;
    private ProjectSpec projectSpec;
    private String target;

    Main() throws IOException {
        target = "D:\\repo\\08\\mygen\\src\\main\\resources\\target";
        configuration = new Configuration(Configuration.getVersion());
        configuration.setClassLoaderForTemplateLoading(Main.class.getClassLoader(), "/");
        configuration.setDefaultEncoding("utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        URL resource = Main.class.getResource("/model.json");
        projectSpec = objectMapper.readValue(resource, ProjectSpec.class);

    }

    public void run() throws IOException, TemplateException {
        pom();
        gitignore();
        src();

    }

    private void src() throws IOException, TemplateException {
        String join = String.join("\\", projectSpec.getGroupId().split("'.' 46"));
        String artifactId = projectSpec.getArtifactId();
        Path dir = Paths.get(target + "\\src\\main\\java\\"
                + join + "\\" + artifactId);
        Files.createDirectories(dir);
        Path appFile = Paths.get(dir.toString()+"\\" +
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
