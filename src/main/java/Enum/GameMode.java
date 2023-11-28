package Enum;

public enum GameMode {
    TOURNAMENT("Tournament"),
    SINGLE_PLAYER("Single Player");

    private final String modeName;

    GameMode(String modeName) {
        this.modeName = modeName;
    }

    public String getModeName() {
        return modeName;
    }

    // You can add other methods or properties specific to each game mode if needed
}
