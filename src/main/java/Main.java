import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Cleanup;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;

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
