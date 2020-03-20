import com.github.converter.*;

public class Main {

    public static void main(String[] args) {
        AbstractConverter[] abstractConverters = new AbstractConverter[]{
                new PomConverter(),
                new GitignoreConverter(),
                new ApplicationPropertiesConverter(),
                new DomainConverter()
        };

        for (AbstractConverter converter : abstractConverters) {
            converter.converte();
        }
    }
}
