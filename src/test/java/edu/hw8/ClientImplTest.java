package edu.hw8;

import edu.hw8.task1.ClientImpl;
import edu.hw8.task1.ServerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class ClientImplTest {
    static Map<String, String> quotesExample = Map.of("личности", "Не переходи на личности там, где их нет"
        , "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        , "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        , "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    @Test
    void getQuote() {
        new Thread(() -> ServerImpl.startServer(3, quotesExample, 1)).start();
        Assertions.assertEquals(quotesExample.get("оскорбления"), ClientImpl.getQuote("оскорбления"));
    }

    @Test
    void getQuoteUnknownWord() {
        new Thread(() -> ServerImpl.startServer(3, quotesExample, 1)).start();
        Assertions.assertEquals("No quote for the word", ClientImpl.getQuote("привет"));
    }

    @Test
    void getQuotes() {
        List<String> words = List.of("оскорбления", "личности", "глупый", "интеллект", "глупый");
        new Thread(() -> ServerImpl.startServer(3, quotesExample, 5)).start();
        List<String> quotes = ClientImpl.getQuotes(words);
        Assertions.assertEquals(5, quotes.size());
    }

}
