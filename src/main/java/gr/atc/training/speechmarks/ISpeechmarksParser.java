/**
 * This source code is property of ATC S.A. Re-distribution of the source code as a whole or in
 * single files isn't allowed. Any derivative of this source code is also property of ATC S.A. and
 * isn't allowed to re-distribute of publish online.
 */
package gr.atc.training.speechmarks;

import java.util.stream.Stream;

public interface ISpeechmarksParser {
  /**
   * Returns the sentence in the specific timestamp
   *
   * @param time The time in milliseconds inside the audio file
   * @return The sentence heard in the specific time
   * @throws SpeechmarkException if an invalid JSON or an invalid timestamp is used
   */
  String getSentence(long time) throws SpeechmarkException;

  /**
   * Return sentences that are smaller (<) in length than a specific size. It uses the length of the
   * string and not the start and end fields
   *
   * @param size The size to do the comparison
   * @return The sentences in the file smaller than the specific size
   * @throws SpeechmarkException if an invalid JSON is used
   */
  Stream<String> getSentencesSmallerThan(int size) throws SpeechmarkException;
}
