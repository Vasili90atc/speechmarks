package gr.atc.training.speechmarks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author Chronis Basilis
 */
@AllArgsConstructor
@NoArgsConstructor
public class Speechmark implements Serializable {
    private static final long serialVersionUID = 1L;
	@Getter
    @Setter
    @JsonProperty(value = "end")
    private Long end;
    @Getter
    @Setter
    @JsonProperty(value = "value")
    private String value;

    @Getter
    @Setter
    @JsonProperty(value = "start")
    private Long start;

    @Getter
    @Setter
    @JsonProperty(value = "time")
    private Long time;

    @Getter
    @Setter
    @JsonProperty(value = "type")
    private String type;

	public Speechmark(long l, String string, long m, long n, String string2) {
		end = l;
		value = string;
		start = m;
		time = n;
		type = string2;
	}

	public long getTime() {
		return time;
	}

	public String getValue() {
		return value;
	}

	public Long getEnd() {
		return end;
	}

	public Long getStart() {
		return start;
	}

	public Object getType() {
		return type;
	}

}
