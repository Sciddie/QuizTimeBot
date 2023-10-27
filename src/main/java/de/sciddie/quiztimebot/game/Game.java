package de.sciddie.quiztimebot.game;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;

import java.security.PrivateKey;
import java.util.HashMap;

public class Game {
    private PrivateChannel gameLeaderChannel; //may be null shortly after creation
    private final Channel gameChannel;
    private int[] questionNumber;  //Position in the Question File
    private int[] teamPoints;
    private final String[] teamNames;
    public Game(String[] teamNames, User gameleaderId, Channel gameChannel) {
        this.gameChannel = gameChannel;
        gameleaderId.openPrivateChannel().queue(privateChannel -> this.gameLeaderChannel = privateChannel);
        this.teamNames = teamNames;
        this.teamPoints = new int[teamNames.length];        //Creates List with the length of teams (Init values are all 0)
        this.questionNumber = new int[teamNames.length];
    }
}
