package trivia.use_case.take_quiz;

import java.util.List;
import java.util.Objects;

/** Data to display after starting a quiz or submitting an answer. */
public final class QuizOutputData {
    private final String participantName;
    private final String quizTitle;
    private final int answeredCount;
    private final int totalQuestions;
    private final int score;
    private final String questionText;
    private final List<String> answerChoices;
    private final boolean finished;
    private final boolean resultSaved;
    private final String statusMessage;

    public QuizOutputData(String participantName, String quizTitle, int answeredCount,
                          int totalQuestions, int score, String questionText,
                          List<String> answerChoices, boolean finished,
                          boolean resultSaved, String statusMessage) {
        this.participantName = participantName;
        this.quizTitle = quizTitle;
        this.answeredCount = answeredCount;
        this.totalQuestions = totalQuestions;
        this.score = score;
        this.questionText = questionText;
        this.answerChoices = List.copyOf(Objects.requireNonNull(answerChoices, "Answer choices are required."));
        this.finished = finished;
        this.resultSaved = resultSaved;
        this.statusMessage = statusMessage;
    }

    public String getParticipantName() {
        return participantName;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public int getAnsweredCount() {
        return answeredCount;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswerChoices() {
        return answerChoices;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isResultSaved() {
        return resultSaved;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
