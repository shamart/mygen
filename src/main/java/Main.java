import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Cleanup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassLoaderForTemplateLoading(Main.class.getClassLoader(), "/");
        configuration.setDefaultEncoding("utf-8");


        @Cleanup Writer out = new FileWriter(new File("D:\\repo\\08\\mygen\\src\\main\\resources\\new.java"));

        ObjectMapper objectMapper = new ObjectMapper();
        URL resource = Main.class.getResource("/model.json");
        ProjectSpec value = new ProjectSpec();
        value.setArtifactId("1");
        value.setGroupId("2");
        value.setVersion("3");
        value.setDescription("5");
        ArrayList<DomainSpec> domains1 = new ArrayList<>();
        DomainField domainField = new DomainField("String", "username",
                new DomainFieldValidation("unique", null));
        DomainSpec e = new DomainSpec();
        ArrayList<DomainField> domains = new ArrayList<>();
        domains.add(domainField);
        e.setProperties(domains);
        e.setName("User");
        domains1.add(e);
        value.setDomains(domains1);

        String s = objectMapper.writeValueAsString(value);
        System.out.println(s);
//        ProjectSpec projectSpec = objectMapper.readValue(resource, ProjectSpec.class);
//        Template template = configuration.getTemplate("target/test.ftl");
//
//        System.out.println(projectSpec);
//        template.process(domainSpec, out);


    }
}
