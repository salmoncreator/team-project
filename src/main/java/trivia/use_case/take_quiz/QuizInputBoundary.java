package trivia.use_case.take_quiz;

/** Commands supported by the take-trivia-quiz use case. */
public interface QuizInputBoundary {
    void startQuiz(String participantName, String quizId);
    void submitAnswer(Integer selectedAnswerIndex);
}
