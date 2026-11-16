package entity;

import java.util.List;

public class Question {
    private final String questionText;
    private final List<String> answerChoices;
    private final int correctAnswerIndex;

    public Question(String questionText, List<String> answerChoices, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answerChoices = answerChoices;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswerChoices() {
        return answerChoices;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
