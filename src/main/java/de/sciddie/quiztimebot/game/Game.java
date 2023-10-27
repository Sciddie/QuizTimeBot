package de.sciddie.quiztimebot.game;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.security.PrivateKey;
import java.util.HashMap;

public class Game {
    public PrivateChannel gameLeaderChannel; //may be null shortly after creation
    private final Channel gameChannel;
    private int[] questionNumber;  //Position in the Question File
    private int[] teamPoints;
    private final String[] teamNames;
    private HashMap<String,Integer> teamNums;
    public Game(String[] teamNames, User gameleaderId, Channel gameChannel) {
        this.gameChannel = gameChannel;
        gameleaderId.openPrivateChannel().queue(privateChannel -> this.gameLeaderChannel = privateChannel);
        this.teamNames = teamNames;
        for (int i = 0; i<teamNames.length; i++) {
            teamNums.put(teamNames[i], i);
        }
        this.teamPoints = new int[teamNames.length];        //Creates List with the length of teams (Init values are all 0)
        this.questionNumber = new int[teamNames.length];
    }

    public void nextQuestion(TextChannel channel, String teamName) {
        int index = teamNums.get(teamName);
        questionNumber[index]++;
    }

    public void addPoint(String teamName) {
        int index = teamNums.get(teamName);
        teamPoints[index]++;
    }

    private void question1(TextChannel channel) {
        // Osteria da Gino Kosten zusammen rechnen Karte
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Geht zum Osteria da Gino")
                .setDescription("Schaut euch die Karte außen an.\nWie viel Kostet alles zusammen?");
        channel.sendMessageEmbeds(eb.build()).addActionRow(
                Button.primary("next", ""),
                Button.primary("next", ""),
                Button.primary("right", "") //TODO: Werte Einfügen
        ).queue();
    }
    private void question2(TextChannel channel) {
        // Schriftfarbe Logo (Multiple)
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Osteria da Gino")
                .setDescription("Weche schriftfarbe hat das Logo?");
        channel.sendMessageEmbeds(eb.build()).addActionRow(
                Button.primary("right", ""),
                Button.primary("next", ""),
                Button.primary("next", "") //TODO: Werte Einfügen
        ).queue();
    }
    private void question3(TextChannel channel) {
        //  nenne zwei Läden, die da noch sind

    }
    private void question4(TextChannel channel) {
        // Rathaus Name Oberbürgermeister (Thomas Sprißler(Multiple)
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Geht zum Rathaus")
                .setDescription("Wie heißt unser Oberbürgermeister?");
        channel.sendMessageEmbeds(eb.build()).addActionRow(
                Button.primary("next", ""),
                Button.primary("next", ""),
                Button.primary("right", "") //TODO: Werte Einfügen
        ).queue();
    }
    private void question5(TextChannel channel) {
        // Rathaus: Bild mit dem Stadt Miniatur
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Rathaus")
                .setDescription("Schickt Noam ein Bild mit der Stadt Miniatur");
        channel.sendMessageEmbeds(eb.build()).addActionRow(
                Button.success("next", "Weiter")
        ).queue();
    }
    private void question6(TextChannel channel) {
        // Ali Gebäudereinigung (Straßenname von zwischen Straße (Metziggasse)
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Gehe zu Ali Gebäudereinigung")
                .setDescription("Wie Lautet der name der Zwischenstraße?");
        channel.sendMessageEmbeds(eb.build()).addActionRow(
                Button.primary("right", ""),
                Button.primary("next", ""),
                Button.primary("next", "")//TODO: Werte Einfügen
        ).queue();
    }
    private void question7(TextChannel channel) {
        // von da aus Foto mit der Stiftskirche)
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Ali Gebäudereinigung")
                .setDescription("Schickt Noam ein Bild mit euch und der Stiftskirche");
        channel.sendMessageEmbeds(eb.build()).addActionRow(
                Button.success("next", "Weiter")//TODO: Werte Einfügen
        ).queue();
    }
    private void question8(TextChannel channel) {
        // Stiftskirche (Glocken, die draußen stehen (2)
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Geht zur Stiftskirche")
                .setDescription("Wie viele Glocken Stehen draußen");
        channel.sendMessageEmbeds(eb.build()).addActionRow(
                Button.primary("right", ""),
                Button.primary("next", ""),
                Button.primary("next", "")//TODO: Werte Einfügen
        ).queue();
    }
    private void question9(TextChannel channel) {
        // Bild mit der Stadt Selfie+
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Stiftskirche")
                .setDescription("Schickt Noam ein Bild mit euch und der Stadt");
        channel.sendMessageEmbeds(eb.build()).addActionRow(
                Button.success("next", "Weiter")//TODO: Werte Einfügen
        ).queue();
    }
    private void question10(TextChannel channel) {
        //  Welche Farbe hat, die Stiftskirchen Uhr vom Schlossberg aus? (rot)(multiple))
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Stiftskirche")
                .setDescription("Welche Farbe hat die Stiftskirchen Uhr vom Schlossberg aus?");
        channel.sendMessageEmbeds(eb.build()).addActionRow(
                Button.primary("right", ""),
                Button.primary("next", ""),
                Button.primary("next", "")//TODO: Werte Einfügen
        ).queue();
    }
    private void question11(TextChannel channel) {
        // Unser Haus von hinten Fotografieren mit allen Beteiligten der Gruppe
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Stiftskirche")
                .setDescription("Schickt Noam ein Bild mit euch und seinem Haus von Hinten");
        channel.sendMessageEmbeds(eb.build()).addActionRow(
                Button.success("next", "Weiter")//TODO: Werte Einfügen
        ).queue();
    }
    private void question12(TextChannel channel) {
        // Spitalkirche (Katholische oder Evangelische Kirche)
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Geht zur Spitalkirche")
                .setDescription("Ist die Spitalkirche Evangelisch oder Katholisch?");
        channel.sendMessageEmbeds(eb.build()).addActionRow(
                Button.primary("right", "Evangelisch"),
                Button.primary("next", "Katholisch")
        ).queue();
    }
}
