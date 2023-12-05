package edu.hw8;

import edu.hw8.task1.ClientImpl;
import edu.hw8.task1.ServerImpl;
import org.junit.jupiter.api.Test;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ServerImplTest {
    static Map<String, String> quotesExample = Map.of("личности", "Не переходи на личности там, где их нет"
        , "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        , "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        , "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    @Test
    void startServer() {
        try (ServerImpl server = new ServerImpl(quotesExample, 3, 8080)) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
