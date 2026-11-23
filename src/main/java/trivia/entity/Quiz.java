package trivia.entity;

import java.util.List;

/** A named collection of one or more trivia questions. */
public final class Quiz {
    private final String id;
    private final String title;
    private final List<Question> questions;

    public Quiz(String id, String title, List<Question> questions) {
        if (id == null || id.isBlank() || title == null || title.isBlank()) {
            throw new IllegalArgumentException("Quiz ID and title are required.");
        }
        if (questions == null || questions.isEmpty()) {
            throw new IllegalArgumentException("A quiz must contain at least one question.");
        }
        for (Question question : questions) {
            if (question == null) {
                throw new IllegalArgumentException("Quiz questions must not be null.");
            }
        }
        this.id = id;
        this.title = title;
        this.questions = List.copyOf(questions);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public int getQuestionCount() {
        return questions.size();
    }
}
