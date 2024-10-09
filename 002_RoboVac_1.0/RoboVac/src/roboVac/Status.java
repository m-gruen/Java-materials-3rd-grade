package roboVac;

public enum Status {
    WALL('#'),
    CLEAN(' '),
    DIRTY('.');

    public final char label;

    private Status(char label) {
        this.label = label;
    }

    public static Status valueOf(char label) {
        for (Status s : Status.values()) {
            if (s.label == label) {
                return s;
            }
        }
        return null;
    }
}
