package trivia.use_case.take_quiz;

/** Presenter port: the interactor does not know about Swing. */
public interface QuizOutputBoundary {
    void present(QuizOutputData output);
    void presentError(String message);
}
