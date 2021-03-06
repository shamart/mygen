import com.github.converter.*;

public class Main {

    public static void main(String[] args) {
        AbstractConverter[] abstractConverters = new AbstractConverter[]{
                new PomConverter(),
                new GitignoreConverter(),
                new ApplicationPropertiesConverter(),
                new DomainConverter(),
                new RepositoryConverter(),
                new ServiceConverter(),
                new ConfigConverter(),
                new HandlerConverter(),
                new DTOConverter(),
                new ControllerConverter(),
                new SpringApplicationConverter()
        };

        for (AbstractConverter converter : abstractConverters) {
            converter.convert();
        }
    }
}
