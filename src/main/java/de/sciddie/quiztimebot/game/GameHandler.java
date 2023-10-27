package de.sciddie.quiztimebot.game;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameHandler {
    public static TextChannel channelRed;
    public static TextChannel channelBlue;
    public static TextChannel channelMagenta;
    public static TextChannel channelGreen;
    private static Game game;
    private static Message selectionMessage;

    public static Game getGame() {
        return game;
    }

    public static void stopGame() {
        game = null;
    }

    public static Game startGame(User gameleaderId, Channel gameChannelId, String[] teamNames) {
        Game game = new Game(teamNames, gameleaderId, gameChannelId);
        GameHandler.game = game;
        return game;
    }

    public static void sendTeamSelectionMessage(SlashCommandInteractionEvent event) {
        List<Role> roles = event.getGuild().getRoles();
        List<String> rolenames = new ArrayList<>();
        for (Role i : roles) {
            rolenames.add(i.getName());
        }
        if (!rolenames.contains("Rot")) {
            Role role = event.getGuild().createRole()
                    .setName("Rot")
                    .setColor(Color.RED).complete();
            channelRed.upsertPermissionOverride(role).setAllowed(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
        }
        if (!rolenames.contains("Blau")) {
            Role role = event.getGuild().createRole()
                    .setName("Blau")
                    .setColor(Color.BLUE).complete();
            channelBlue.upsertPermissionOverride(role).setAllowed(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
        }
        if (!rolenames.contains("Magenta")) {
            Role role = event.getGuild().createRole()
                    .setName("Magenta")
                    .setColor(Color.MAGENTA).complete();
            channelMagenta.upsertPermissionOverride(role).setAllowed(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
        }
        if (!rolenames.contains("Grün")) {
            Role role = event.getGuild().createRole()
                    .setName("Grün")
                    .setColor(Color.GREEN).complete();
            channelGreen.upsertPermissionOverride(role).setAllowed(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
        }

        EmbedBuilder start = new EmbedBuilder();
        start.setTitle("Start Game");
        start.setDescription("Erst wenn jeder in einem Team ist!");
        event.replyEmbeds(start.build()).setEphemeral(true).addActionRow(net.dv8tion.jda.api.interactions.components.buttons.Button.success("start", "Starten")).queue();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Wilkommen bei QuizTime");
        eb.setDescription("Wähle dein Team!");
        event.getHook().sendMessageEmbeds(eb.build()).addActionRow(
                Button.primary("blue", "Blau"),
                Button.danger("red", "Rot"),
                Button.secondary("magenta", "Magenta"),
                Button.success("green", "Grün")
        ).queue((message -> GameHandler.selectionMessage = message
        ));
    }

    public static TextChannel[] getChannelList(MessageEmbed embed) {
        return new TextChannel[] {channelMagenta,channelRed,channelBlue,channelGreen};
    }
}