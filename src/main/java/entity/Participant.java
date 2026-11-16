package entity;

import java.util.List;

/**
 * A simple entity representing a Participant. Users have a username and results.
 */
public class Participant {

    private final String name;
    private final List<QuizResult> results;

    /**
     *
     */
    public Participant(String name, List<QuizResult> results) {
        this.name = name;
        this.results = results;
    }

    public String getName() {
        return name;
    }

    public List<QuizResult> getResults() {
        return results;
    }

}

