package trivia.entity;

import java.util.List;
import java.util.Objects;

/** A completed quiz attempt, including its individual answers. */
public final class QuizResult {
    private final String resultId;
    private final String participantName;
    private final String quizId;
    private final String quizTitle;
    private final int score;
    private final int totalQuestions;
    private final List<SubmittedAnswer> answers;

    public QuizResult(String resultId, String participantName, String quizId,
                      String quizTitle, int score, int totalQuestions,
                      List<SubmittedAnswer> answers) {
        this.resultId = Objects.requireNonNull(resultId, "Result ID is required.");
        this.participantName = Objects.requireNonNull(participantName, "Participant name is required.");
        this.quizId = Objects.requireNonNull(quizId, "Quiz ID is required.");
        this.quizTitle = Objects.requireNonNull(quizTitle, "Quiz title is required.");
        if (totalQuestions < 0 || score < 0 || score > totalQuestions) {
            throw new IllegalArgumentException("Invalid score or question count.");
        }
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.answers = List.copyOf(Objects.requireNonNull(answers, "Answers are required."));
    }

    public String getResultId() {
        return resultId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public String getQuizId() {
        return quizId;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public List<SubmittedAnswer> getAnswers() {
        return answers;
    }
}
