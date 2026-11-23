package trivia.app;

import trivia.data_access.InMemoryQuizDataAccessObject;
import trivia.data_access.JsonFileQuizResultDataAccessObject;
import trivia.interface_adapter.take_quiz.QuizController;
import trivia.interface_adapter.take_quiz.QuizPresenter;
import trivia.interface_adapter.take_quiz.QuizViewModel;
import trivia.use_case.take_quiz.QuizInteractor;
import trivia.view.QuizView;

import javax.swing.SwingUtilities;
import java.nio.file.Path;

/** Wires the Clean Architecture layers together. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InMemoryQuizDataAccessObject quizRepository =
                    InMemoryQuizDataAccessObject.withSampleQuizzes();
            JsonFileQuizResultDataAccessObject resultRepository =
                    new JsonFileQuizResultDataAccessObject(Path.of("data", "results.jsonl"));
            QuizViewModel viewModel = new QuizViewModel();
            QuizPresenter presenter = new QuizPresenter(viewModel);
            QuizInteractor interactor = new QuizInteractor(quizRepository, resultRepository, presenter);
            QuizController controller = new QuizController(interactor);
            QuizView view = new QuizView(controller, viewModel, quizRepository.getAvailableQuizzes());
            view.setVisible(true);
        });
    }
}
