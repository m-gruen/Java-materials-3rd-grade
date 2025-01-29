public enum Group {
    GESAMT("Gesamtgruppe"),
    QUALIFIKATION("Qualifikationsgruppe"),
    MEISTER("Meistergruppe");

    private final String displayName;

    Group(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}