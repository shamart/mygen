package ${groupId}.${artifactId};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ${artifactId?cap_first}Application {

    public static void main(String[] args) {
        SpringApplication.run(${artifactId?cap_first}Application.class, args);
    }

}
