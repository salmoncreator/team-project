package trivia.use_case.take_quiz;

import trivia.entity.Quiz;
import java.util.List;
import java.util.Optional;

/** Input port for retrieving available quizzes. */
public interface QuizDataAccessInterface {
    List<Quiz> getAvailableQuizzes();
    Optional<Quiz> findById(String quizId);
}
