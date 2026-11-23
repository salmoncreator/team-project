package trivia.use_case.take_quiz;

import trivia.entity.Participant;
import trivia.entity.Question;
import trivia.entity.Quiz;
import trivia.entity.QuizSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/** Executes Use Case 1 from the proposal: take a multiple-choice trivia quiz. */
public final class QuizInteractor implements QuizInputBoundary {
    private final QuizDataAccessInterface quizzes;
    private final QuizResultDataAccessInterface results;
    private final QuizOutputBoundary presenter;
    private QuizSession session;

    public QuizInteractor(QuizDataAccessInterface quizzes,
                          QuizResultDataAccessInterface results,
                          QuizOutputBoundary presenter) {
        this.quizzes = quizzes;
        this.results = results;
        this.presenter = presenter;
    }

    @Override
    public void startQuiz(String participantName, String quizId) {
        if (participantName == null || participantName.isBlank()) {
            presenter.presentError("Enter your name before starting the quiz.");
            return;
        }
        Optional<Quiz> selectedQuiz = quizzes.findById(quizId);
        if (selectedQuiz.isEmpty()) {
            presenter.presentError("Select an available quiz.");
            return;
        }
        if (selectedQuiz.get().getQuestionCount() == 0) {
            presenter.presentError("The selected quiz has no questions.");
            return;
        }
        session = new QuizSession(new Participant(participantName), selectedQuiz.get());
        presentState("Quiz started. Select an answer.", false);
    }

    @Override
    public void submitAnswer(Integer selectedAnswerIndex) {
        if (session == null) {
            presenter.presentError("Start a quiz before submitting an answer.");
            return;
        }
        if (session.isComplete()) {
            presenter.presentError("This quiz has already finished.");
            return;
        }
        if (selectedAnswerIndex == null) {
            // Use Case 1, extension 3a: keep the question visible.
            presenter.presentError("Please select an answer before continuing.");
            return;
        }

        boolean correct;
        try {
            correct = session.submitAnswer(selectedAnswerIndex);
        } catch (IllegalArgumentException ex) {
            presenter.presentError(ex.getMessage());
            return;
        }

        if (!session.isComplete()) {
            // Incorrect answers do not increase the score (extension 4a).
            presentState(correct ? "Correct! Next question." : "Incorrect. Next question.", false);
            return;
        }

        // The attempt is complete. A saving failure must not hide the final score.
        boolean saved;
        String message;
        try {
            results.save(session.createResult());
            saved = true;
            message = "Quiz completed. Your result was recorded.";
        } catch (IOException ex) {
            saved = false;
            message = "Quiz completed, but your result could not be recorded.";
        }
        presentState(message, saved);
    }

    private void presentState(String message, boolean saved) {
        Quiz quiz = session.getQuiz();
        Question question = session.isComplete() ? null : session.getCurrentQuestion();
        presenter.present(new QuizOutputData(
                session.getParticipant().getName(), quiz.getTitle(),
                session.getAnsweredCount(), quiz.getQuestionCount(), session.getScore(),
                question == null ? "" : question.getQuestionText(),
                question == null ? List.of() : question.getAnswerChoices(),
                session.isComplete(), saved, message));
    }
}
