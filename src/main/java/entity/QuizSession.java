package entity;

import java.util.List;

public class QuizSession {
    private Participant participant;
    private Quiz quiz;
    private int currentQuestionIndex;
    private int score;
    private List<SubmittedAnswer> answers;

    public QuizSession(Participant participant, Quiz quiz, int currentQuestionIndex, int score, List<SubmittedAnswer> answers) {
        this.participant = participant;
        this.quiz = quiz;
        this.currentQuestionIndex = currentQuestionIndex;
        this.score = score;
        this.answers = answers;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public int getScore() {
        return score;
    }

    public List<SubmittedAnswer> getAnswers() {
        return answers;
    }
}
