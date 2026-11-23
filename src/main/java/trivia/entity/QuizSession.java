package trivia.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Tracks a single attempt and encapsulates score/progress updates. */
public final class QuizSession {
    private final Participant participant;
    private final Quiz quiz;
    private final List<SubmittedAnswer> answers = new ArrayList<>();
    private int currentQuestionIndex;
    private int score;

    public QuizSession(Participant participant, Quiz quiz) {
        if (participant == null || quiz == null) {
            throw new IllegalArgumentException("Participant and quiz are required.");
        }
        this.participant = participant;
        this.quiz = quiz;
    }

    /** Submit one answer, then advance to the next question; returns its correctness. */
    public boolean submitAnswer(int answerIndex) {
        if (isComplete()) {
            throw new IllegalStateException("The quiz is already complete.");
        }
        Question question = getCurrentQuestion();
        if (answerIndex < 0 || answerIndex >= question.getAnswerChoices().size()) {
            throw new IllegalArgumentException("Select one of the displayed answers.");
        }
        boolean correct = question.isCorrect(answerIndex);
        answers.add(new SubmittedAnswer(currentQuestionIndex, answerIndex,
                question.getCorrectAnswerIndex()));
        if (correct) {
            score++;
        }
        currentQuestionIndex++;
        return correct;
    }

    public Question getCurrentQuestion() {
        if (isComplete()) {
            throw new IllegalStateException("There are no more questions.");
        }
        return quiz.getQuestion(currentQuestionIndex);
    }

    public boolean isComplete() {
        return currentQuestionIndex == quiz.getQuestionCount();
    }

    public int getScore() {
        return score;
    }

    public int getAnsweredCount() {
        return currentQuestionIndex;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public QuizResult createResult() {
        if (!isComplete()) {
            throw new IllegalStateException("Cannot record an incomplete quiz.");
        }
        return new QuizResult(UUID.randomUUID().toString(), participant.getName(), quiz.getId(),
                quiz.getTitle(), score, quiz.getQuestionCount(), answers);
    }
}
