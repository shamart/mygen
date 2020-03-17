import com.github.converter.AbstractConverter;
import com.github.converter.GitignoreConverter;
import com.github.converter.PomConverter;

public class Main {

    public static void main(String[] args) {
        AbstractConverter[] abstractConverters = new AbstractConverter[]{
                new PomConverter(),
                new GitignoreConverter()
        };

        for (AbstractConverter converter : abstractConverters) {
            converter.converte();
        }
    }
}
