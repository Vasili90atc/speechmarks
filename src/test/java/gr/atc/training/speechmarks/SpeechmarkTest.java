package gr.atc.training.speechmarks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SpeechmarkTest {

    @Test
    void createSpeechmarkTest() {

        Speechmark speechMark = new Speechmark(1234L, "HELLO WORLD!!!", 2343L, 5000L, "sentence");

        assertAll(
            () -> assertEquals(1234L, speechMark.getEnd(), "Error in end pr"),
            () -> assertEquals("HELLO WORLD!!!", speechMark.getValue(), "Error in value pr"),
            () -> assertEquals(2343L, speechMark.getStart(), "Error in start pr"),
            () -> assertEquals(5000L, speechMark.getTime(), "Error in time pr"),
            () -> assertEquals("sentence", speechMark.getType(), "Error in type")
        );
    }

}
