/**
 * This source code is property of ATC S.A. Re-distribution of the source code as a whole or in
 * single files isn't allowed. Any derivative of this source code is also property of ATC S.A. and
 * isn't allowed to re-distribute of publish online.
 */
package gr.atc.training.speechmarks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpeechmarksTest {

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    @Test
    void testValidTimestamps() throws Exception {
        InputStream inputStream = SpeechmarksTest.class.getResourceAsStream("/test.json");
        String json = null;
        try {
            json = readFromInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpeechmarksParser parser = new SpeechmarksParser(json);

        String sentence = parser.getSentence(0l);
        assertEquals(sentence, "Peace: it's wonderful.");
        sentence = parser.getSentence(9500l);
        assertEquals(
            sentence,
            "Nevertheless my thesis in this essay is that we are likely soon to regret the passing of"
                + " the Cold War.");
        sentence = parser.getSentence(1539997);
        assertEquals(
            sentence,
            "Proponents ignore the evidence of several wars since 1945, as well as several"
                + " campaign-ending battles of the Second World War, that it is still possible to gain"
                + " a quick and decisive victory on the conventional battlefield and avoid the"
                + " devastation of a protracted conflict.");
        sentence = parser.getSentence(1554681);
        assertEquals(
            sentence,
            "Proponents ignore the evidence of several wars since 1945, as well as several"
                + " campaign-ending battles of the Second World War, that it is still possible to gain"
                + " a quick and decisive victory on the conventional battlefield and avoid the"
                + " devastation of a protracted conflict.");
        sentence = parser.getSentence(1554682);
        assertEquals(
            sentence,
            "Conventional wars can be won rather cheaply; nuclear war cannot be, because neither side"
                + " can escape devastation by the other, regardless of what happens on the"
                + " battlefield.");
        sentence = parser.getSentence(2763355);
        assertEquals(
            sentence, "And even if they are followed, peace in Europe will not be guaranteed.");
        sentence = parser.getSentence(2768480);
        assertEquals(
            sentence,
            "If the Cold War is truly behind us, therefore, the stability of the past forty-five years"
                + " is not likely to be seen again in the coming decades.");
    }

    @Test
    void testInvalidTimestamps() throws Exception {
        InputStream inputStream = SpeechmarksTest.class.getResourceAsStream("/test.json");
        String json = null;
        try {
            json = readFromInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpeechmarksParser parser = new SpeechmarksParser(json);
        assertThrows(
            SpeechmarkException.class,
            () -> {
                parser.getSentence(-100l);
            });
        assertThrows(
            SpeechmarkException.class,
            () -> {
                parser.getSentence(2768481l);
            });
    }

    @Test
    void testSmallSentences() throws Exception {
        InputStream inputStream = SpeechmarksTest.class.getResourceAsStream("/test.json");
        String json = null;
        try {
            json = readFromInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpeechmarksParser parser = new SpeechmarksParser(json);
        List<String> sentences = parser.getSentencesSmallerThan(10).collect(Collectors.toList());
        assertEquals(sentences.size(), 1);
        assertEquals(sentences.get(0), "state.");
        assertEquals(parser.getSentencesSmallerThan(50).count(), 36);
    }

    @Test
    void nullData() {
        SpeechmarksParser parser = new SpeechmarksParser(null);
        assertThrows(SpeechmarkException.class, () -> parser.getSentencesSmallerThan(0));
        assertThrows(SpeechmarkException.class, () -> parser.getSentencesSmallerThan(-10));
        assertThrows(SpeechmarkException.class, () -> parser.getSentence(60000000000L));
    }

}
