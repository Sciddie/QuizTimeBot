package de.sciddie.quiztimebot.game;

import java.util.HashMap;

public class GameHandler {
    private HashMap<String , Game> games = new HashMap<>();

    public Game getGame(String ServerId) {
        return games.get(ServerId);
    }

    public void stopGame(String id) {
        games.remove(id);
    }

    public Game startGame(String serverId, String[] teamNames, String gameleaderChannelId) {
        Game game = new Game(teamNames, gameleaderChannelId);
        games.put(serverId, game);
        return game;
    }
}