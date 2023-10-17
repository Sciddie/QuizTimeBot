package de.sciddie.quiztimebot.game;

import java.util.HashMap;

public class Game {
    private final String gameleaderChannelId;
    private int questionNumber = 0;  //Position in the Question File
    private int[] teamPoints;
    private final String[] teamNames;
    public Game(String[] teamNames, String gameleaderChannelId) {
        this.gameleaderChannelId = gameleaderChannelId;
        this.teamNames = teamNames;
        this.teamPoints = new int[teamNames.length]; //Creates List with the length of teams (Init values are all 0)
    }
}
