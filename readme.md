# Parse Speechmark files
The goal of this project is to provide a method for parsing a speechmarks file. A speechmarks file is created when "transcribing" an mp3 file. The audio is converted to text and the speechmarks file provides time information for every sentence. The file is in json format and it has the following structure:

* speechMarks: array of speechmark objects
* duration: total duration of the mp3 file in milliseconds

Speechmark object:

* start: index of first character of the sentence in the block.
* end: index of last character of the sentence in the block.
* value: Text of the sentence.
* type: "sentence" or "word". Always "sentence" for this case.
* time: The time in milliseconds, when the sentence starts playing in the MP3 file.

The goal of the project is to provide a method for finding the sentence in the mp3 file from a timestamp in milliseconds. For example for the [test.json](src/test/resources/test.json) file, if the 9,500 timestamp is given, the "Nevertheless my thesis in this essay is that we are likely soon to regret the passing of the Cold War." should be returned. This sentence starts at 9157 and the next one starts at 14,506.

## Exercise
To complete the exercise you must implement the [SpeechmarksParser class](src/main/java/gr/atc/training/speechmarks/SpeechmarksParser.java). You are allowed to add new dependencies to [pom.xml](pom.xml), add new fields and private methods to the class, but you must not modify the interface.

The following methods must be implemented:

* SpeechmarksParser::getSentence
* SpeechmarksParser::getSentencesSmallerThan

The tests in [SpeechmarksTest.java](src/test/java/gr/atc/training/speechmarks/SpeechmarksTest.java) must both pass. The instructions in this readme and the javadoc should be enough for completing the task. In any case, you can consider the unit tests as providing the specifications.

To submit your solution, clone the repository, implement the class and issue a pull request.