package trivia.data_access;

import trivia.entity.QuizResult;
import trivia.entity.SubmittedAnswer;
import trivia.use_case.take_quiz.QuizResultDataAccessInterface;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/** Saves completed results as one JSON object per line in results.jsonl. */
public final class JsonFileQuizResultDataAccessObject implements QuizResultDataAccessInterface {
    private final Path file;

    public JsonFileQuizResultDataAccessObject(Path file) {
        this.file = file;
    }

    @Override
    public void save(QuizResult result) throws IOException {
        Path parent = file.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.writeString(file, toJson(result) + System.lineSeparator(), StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private static String toJson(QuizResult result) {
        StringBuilder json = new StringBuilder();
        json.append("{\"resultId\":").append(quote(result.getResultId()));
        json.append(",\"participantName\":").append(quote(result.getParticipantName()));
        json.append(",\"quizId\":").append(quote(result.getQuizId()));
        json.append(",\"quizTitle\":").append(quote(result.getQuizTitle()));
        json.append(",\"score\":").append(result.getScore());
        json.append(",\"totalQuestions\":").append(result.getTotalQuestions());
        json.append(",\"answers\":[");
        for (int i = 0; i < result.getAnswers().size(); i++) {
            if (i > 0) {
                json.append(',');
            }
            SubmittedAnswer answer = result.getAnswers().get(i);
            json.append("{\"questionIndex\":").append(answer.getQuestionIndex());
            json.append(",\"selectedAnswerIndex\":").append(answer.getSelectedAnswerIndex());
            json.append(",\"correctAnswerIndex\":").append(answer.getCorrectAnswerIndex());
            json.append(",\"correct\":").append(answer.isCorrect()).append('}');
        }
        return json.append("]}").toString();
    }

    /** Minimal, standards-compliant JSON escaping without third-party libraries. */
    private static String quote(String value) {
        StringBuilder quoted = new StringBuilder("\"");
        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);
            switch (character) {
                case '"' -> quoted.append("\\\"");
                case '\\' -> quoted.append("\\\\");
                case '\n' -> quoted.append("\\n");
                case '\r' -> quoted.append("\\r");
                case '\t' -> quoted.append("\\t");
                default -> {
                    if (character < 0x20) {
                        quoted.append(String.format("\\u%04x", (int) character));
                    } else {
                        quoted.append(character);
                    }
                }
            }
        }
        return quoted.append('"').toString();
    }
}
