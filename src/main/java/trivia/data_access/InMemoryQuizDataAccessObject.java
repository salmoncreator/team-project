package trivia.data_access;

import trivia.entity.Question;
import trivia.entity.Quiz;
import trivia.use_case.take_quiz.QuizDataAccessInterface;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** An adapter for available quizzes. Replace with the team's quiz storage later. */
public final class InMemoryQuizDataAccessObject implements QuizDataAccessInterface {
    private final Map<String, Quiz> quizzes = new LinkedHashMap<>();

    public InMemoryQuizDataAccessObject(List<Quiz> initialQuizzes) {
        for (Quiz quiz : initialQuizzes) {
            quizzes.put(quiz.getId(), quiz);
        }
    }

    @Override
    public List<Quiz> getAvailableQuizzes() {
        return List.copyOf(quizzes.values());
    }

    @Override
    public Optional<Quiz> findById(String quizId) {
        return Optional.ofNullable(quizzes.get(quizId));
    }

    public static InMemoryQuizDataAccessObject withSampleQuizzes() {
        Quiz basics = new Quiz("general-knowledge", "General Knowledge", List.of(
                new Question("Which planet is known as the Red Planet?",
                        List.of("Venus", "Mars", "Jupiter", "Mercury"), 1),
                new Question("What is 7 × 8?", List.of("54", "56", "64", "49"), 1),
                new Question("Which ocean is the largest?",
                        List.of("Atlantic", "Indian", "Arctic", "Pacific"), 3),
                new Question("What language is primarily used to write Android apps?",
                        List.of("Java or Kotlin", "HTML", "SQL", "CSS"), 0)));
        Quiz computing = new Quiz("computing", "Computing", List.of(
                new Question("What does CPU stand for?",
                        List.of("Central Processing Unit", "Computer Power Unit",
                                "Central Program Utility", "Control Process Unit"), 0),
                new Question("Which data structure typically uses FIFO order?",
                        List.of("Stack", "Binary tree", "Queue", "Set"), 2),
                new Question("Which keyword creates a subclass in Java?",
                        List.of("implements", "extends", "imports", "package"), 1)));
        return new InMemoryQuizDataAccessObject(List.of(basics, computing));
    }
}
