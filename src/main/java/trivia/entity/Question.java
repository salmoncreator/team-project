package trivia.entity;

import java.util.List;

/** An immutable multiple-choice trivia question. */
public final class Question {
    private final String questionText;
    private final List<String> answerChoices;
    private final int correctAnswerIndex;

    public Question(String questionText, List<String> answerChoices, int correctAnswerIndex) {
        if (questionText == null || questionText.isBlank()) {
            throw new IllegalArgumentException("Question text must not be empty.");
        }
        if (answerChoices == null || answerChoices.size() < 2) {
            throw new IllegalArgumentException("A question must have at least two choices.");
        }
        for (String choice : answerChoices) {
            if (choice == null || choice.isBlank()) {
                throw new IllegalArgumentException("Answer choices must not be empty.");
            }
        }
        if (correctAnswerIndex < 0 || correctAnswerIndex >= answerChoices.size()) {
            throw new IllegalArgumentException("Correct answer index is out of range.");
        }
        this.questionText = questionText;
        this.answerChoices = List.copyOf(answerChoices);
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

    public boolean isCorrect(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }
}
