package entity;

import java.util.List;

public class QuizResult {
    private final String participantName;
    private final String quizId;
    private final int score;
    private final int totalQuestions;
    private final List<SubmittedAnswer> answers;

    public QuizResult(String participantName, String quizId, int score, int totalQuestions, List<SubmittedAnswer> answers) {
        this.participantName = participantName;
        this.quizId = quizId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.answers = answers;
    }

    public String getParticipantName() {
        return participantName;
    }

    public String getQuizId() {
        return quizId;
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