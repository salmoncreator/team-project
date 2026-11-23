package trivia.interface_adapter.take_quiz;

import trivia.use_case.take_quiz.QuizOutputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** Observable state for the Swing view. */
public final class QuizViewModel {
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private QuizOutputData state;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    public void setState(QuizOutputData nextState) {
        state = nextState;
        // A null old value always notifies the view, even when contents repeat.
        changes.firePropertyChange("state", null, nextState);
    }

    public QuizOutputData getState() {
        return state;
    }

    public void showError(String error) {
        changes.firePropertyChange("error", null, error);
    }
}
