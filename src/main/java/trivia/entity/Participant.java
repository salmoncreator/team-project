package trivia.entity;

/** A person currently taking a quiz. */
public final class Participant {
    private final String name;

    public Participant(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Participant name must not be empty.");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }
}
