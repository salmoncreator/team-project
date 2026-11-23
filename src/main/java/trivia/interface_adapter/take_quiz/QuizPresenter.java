package trivia.interface_adapter.take_quiz;

import trivia.use_case.take_quiz.QuizOutputBoundary;
import trivia.use_case.take_quiz.QuizOutputData;

/** Updates the view model without depending on Swing widgets. */
public final class QuizPresenter implements QuizOutputBoundary {
    private final QuizViewModel viewModel;

    public QuizPresenter(QuizViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(QuizOutputData output) {
        viewModel.setState(output);
    }

    @Override
    public void presentError(String message) {
        viewModel.showError(message);
    }
}
