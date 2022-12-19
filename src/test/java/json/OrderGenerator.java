package json;
import org.apache.commons.lang3.RandomStringUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class OrderGenerator {
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static LocalDateTime now = LocalDateTime.now();

    public static Order create() {
        return new Order(
                RandomStringUtils.randomAlphabetic(7),
                RandomStringUtils.randomAlphabetic(7),
                RandomStringUtils.randomAlphabetic(7),
                RandomStringUtils.randomAlphabetic(7),
                RandomStringUtils.randomAlphabetic(7),
                RandomStringUtils.randomNumeric(1),
                dtf.format(now).toString(),
                RandomStringUtils.randomAlphabetic(7),
                List.of("")
        );
    }

    public static Order createColored(List<String> color) {
        return new Order(
                RandomStringUtils.randomAlphabetic(7),
                RandomStringUtils.randomAlphabetic(7),
                RandomStringUtils.randomAlphabetic(7),
                RandomStringUtils.randomAlphabetic(7),
                RandomStringUtils.randomAlphabetic(7),
                RandomStringUtils.randomNumeric(1),
                dtf.format(now).toString(),
                RandomStringUtils.randomAlphabetic(7),
                color
        );
    }
}
