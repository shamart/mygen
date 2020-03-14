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
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private ProjectSpec projectSpec;
    private String target;
    private Path mainSrcDir;
    private final String artifactId;

    Main() throws IOException {


        String join = String.join("/", projectSpec.getGroupId().split("'.' 46"));
        artifactId = projectSpec.getArtifactId();
        mainSrcDir = Paths.get(target + "/src/main/java/"
                + join + "/" + artifactId);
    }

    public void run() throws IOException, TemplateException {
        src();
        resource();

    }

    private void resource() throws IOException, TemplateException {
        String resourceDir = target + "/src/main/resources";
        Files.createDirectories(Paths.get(resourceDir));

        transform("/ftp/application.properties",
                resourceDir + "/application.properties",
                projectSpec);
    }

    private void transform(String fromFile, String toFile, Object dataModel) throws IOException, TemplateException {
        @Cleanup FileWriter writer = new FileWriter(toFile);
        Template template = configuration.getTemplate(fromFile);
        template.process(dataModel, writer);
    }

    private void src() throws IOException, TemplateException {


    }

    private void repository(String repositoryDir, DomainSpec domain) throws IOException, TemplateException {
        String name = domain.getName();
        String s = repositoryDir + "/" + StringUtils.capitalize(name) + "Repository" + ".java";
        DomainFileModel domainFileModel = new DomainFileModel(projectSpec, domain);
        transform("/ftp/Repository.java", s, domainFileModel);
    }

    private void domain(String domainDir, DomainSpec domain) throws IOException, TemplateException {
        String name = domain.getName();
        String s = domainDir + "/" + StringUtils.capitalize(name) + ".java";
        DomainFileModel domainFileModel = new DomainFileModel(projectSpec, domain);
        transform("/ftp/Domain.java", s, domainFileModel);
    }





    public static void main(String[] args) throws IOException, TemplateException {
        Main main = new Main();
        main.run();
    }
}
