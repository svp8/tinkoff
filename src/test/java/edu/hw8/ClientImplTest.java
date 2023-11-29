package edu.hw8;

import edu.hw8.task1.ClientImpl;
import edu.hw8.task1.ServerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class ClientImplTest {
    static Map<String, String> quotesExample = Map.of("личности", "Не переходи на личности там, где их нет"
        , "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        , "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        , "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    @Test
    void getQuote() throws Exception {
        String quote = null;
        try (ServerImpl server = new ServerImpl(quotesExample, 3, 8080)) {
            quote = ClientImpl.getQuote("оскорбления");
        }
        Assertions.assertEquals(quotesExample.get("оскорбления"), quote);
    }

    @Test
    void connectionFailed() {
        Assertions.assertThrows(RuntimeException.class, () -> ClientImpl.getQuote("оскорбления"));
    }

    @Test
    void getQuoteUnknownWord() {
        String quote = null;
        try (ServerImpl server = new ServerImpl(quotesExample, 3, 8080)) {
            quote = ClientImpl.getQuote("1213");
        } catch (Exception ignored) {
        }
        Assertions.assertEquals("No quote for the word", quote);
    }

    @Test
    void getQuotes() {
        List<String> words = List.of("оскорбления", "личности", "глупый", "интеллект", "глупый");
        List<String> quotes = null;
        try (ServerImpl server = new ServerImpl(quotesExample, 3, 8080)) {
            quotes = ClientImpl.getQuotes(words);
            ;
        } catch (Exception ignored) {
        }
        Assertions.assertEquals(5, quotes.size());
    }

    @Test
    void getQuotesMoreThanPool() {
        List<String> words = Stream.generate(() -> "abc").limit(20).toList();
        List<String> quotes = null;
        try (ServerImpl server = new ServerImpl(quotesExample, 3, 8080)) {
            quotes = ClientImpl.getQuotes(words);
            ;
        } catch (Exception ignored) {
        }
        Assertions.assertEquals(20, quotes.size());
    }

}
