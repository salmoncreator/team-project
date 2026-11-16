package entity;

public class SubmittedAnswer {
    private final int questionIndex;
    private final int selectedAnswerIndex;
    private final int correctAnswerIndex;

    public SubmittedAnswer(int questionIndex, int selectedAnswerIndex, int correctAnswerIndex) {
        this.questionIndex = questionIndex;
        this.selectedAnswerIndex = selectedAnswerIndex;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public int getSelectedAnswerIndex() {
        return selectedAnswerIndex;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}