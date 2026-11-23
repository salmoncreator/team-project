package trivia.use_case.take_quiz;

import trivia.entity.QuizResult;
import java.io.IOException;

/** Output port for recording a completed attempt. */
public interface QuizResultDataAccessInterface {
    void save(QuizResult result) throws IOException;
}
