package de.sciddie.quiztimebot.game;

import java.util.HashMap;

public class GameHandler {
    private static Game game;

    public static Game getGame() {
        return game;
    }

    public static void stopGame() {
        game = null;
    }

    public static Game startGame(String[] teamNames, String gameleaderChannelId) {
        Game game = new Game(teamNames, gameleaderChannelId);
        GameHandler.game = game;
        return game;
    }
}