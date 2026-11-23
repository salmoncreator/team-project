package trivia.interface_adapter.take_quiz;

import trivia.use_case.take_quiz.QuizInputBoundary;

/** Translates UI actions into calls to the use case. */
public final class QuizController {
    private final QuizInputBoundary interactor;

    public QuizController(QuizInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void startQuiz(String name, String quizId) {
        interactor.startQuiz(name, quizId);
    }

    public void submitAnswer(Integer answerIndex) {
        interactor.submitAnswer(answerIndex);
    }
}
