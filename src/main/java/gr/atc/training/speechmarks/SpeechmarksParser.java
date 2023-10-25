package gr.atc.training.speechmarks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Chronis Basilis
 */
@NoArgsConstructor
public class SpeechmarksParser implements ISpeechmarksParser {

    @Setter
    @JsonProperty(value = "speechMarks")
    private Speechmark[] speechmarks;

    @JsonProperty(value = "duration")
    @Setter
    private Long duration;
    
    @JsonCreator
    public SpeechmarksParser(@JsonProperty("content") String json) {
		this.initSpeechMarksParser(json); // removed extra variable initialization
    }

    private void initSpeechMarksParser(final String speechMarksJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            SpeechmarksParser productNode = objectMapper.readValue(speechMarksJson, SpeechmarksParser.class);
            this.speechmarks = productNode.speechmarks;
            this.duration = productNode.duration;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        catch(IllegalArgumentException e) {
        	// Also catch this exception when input argument is null
        	e.printStackTrace();
        }
    }

    public String getSentence(long time) throws SpeechmarkException {

        if (this.speechmarks == null || !validationTime(time))
            throw new SpeechmarkException();

        return Arrays.stream(this.speechmarks)
            .filter(e -> e.getTime() <= time)
            .reduce((first, second) -> second)
            .orElseThrow(SpeechmarkException::new)
            .getValue();
    }

    private boolean validationTime(Long time) {
        return time >= 0 && time < duration;
    }

    public Stream<String> getSentencesSmallerThan(int size) throws SpeechmarkException {

        if (this.speechmarks == null || !validationSize(size))
            throw new SpeechmarkException();

        return Arrays.stream(this.speechmarks)
            .map(Speechmark::getValue)
            .filter(value -> value.length() < size);
    }

    private boolean validationSize(int size) {
        return size > 0;
    }
}
