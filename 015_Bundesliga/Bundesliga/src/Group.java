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

    public static Group fromDisplayName(String displayName) {
        for (Group group : Group.values()) {
            if (group.getDisplayName().equalsIgnoreCase(displayName)) {
                return group;
            }
        }
        throw new IllegalArgumentException("No enum constant with display name " + displayName);
    }
}