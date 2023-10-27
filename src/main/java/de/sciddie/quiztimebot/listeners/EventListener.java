package de.sciddie.quiztimebot.listeners;

import de.sciddie.quiztimebot.game.GameHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.RestAction;

import java.awt.*;
import java.util.List;

import java.util.ArrayList;

public class EventListener extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getButton().getId().equals("start")) {
            List<Role> roles = event.getGuild().getRoles();
            List<String> teamNameList = new ArrayList<String>();
            for (Role role : roles) {
                if (!event.getGuild().getMembersWithRoles(role).isEmpty()){
                    teamNameList.add(role.getName());
                }
            }
            String[] teamNames = new String[teamNameList.size()];
            teamNames = teamNameList.toArray(teamNames);
            GameHandler.startGame(event.getUser(), event.getChannel(), teamNames);
            ButtonListenerGame.removeButtons(event.getMessage());
            return;
        }

        List<Role> roles = event.getMember().getRoles();
        for (Role role : roles) {
            event.getGuild().removeRoleFromMember(event.getMember(), role).complete();
        }

        if (event.getButton().getId().equals("red")) {
            Role role = event.getJDA().getRolesByName("Rot", true).get(0);
            event.getGuild().addRoleToMember(event.getMember(), role).queue();
            event.deferEdit().queue();
        }
        else if (event.getButton().getId().equals("magenta")) {
            Role role = event.getJDA().getRolesByName("Magenta", true).get(0);
            event.getGuild().addRoleToMember(event.getMember(), role).queue();
            event.deferEdit().queue();
        }
        else if (event.getButton().getId().equals("green")) {
            Role role = event.getJDA().getRolesByName("grün", true).get(0);
            event.getGuild().addRoleToMember(event.getMember(), role).queue();
            event.deferEdit().queue();
        }
        else if (event.getButton().getId().equals("blue")) {
            Role role = event.getJDA().getRolesByName("blau", true).get(0);
            event.getGuild().addRoleToMember(event.getMember(), role).queue();
            event.deferEdit().queue();
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("start")) {
            GameHandler.channelRed = event.getOption("rot").getAsChannel().asTextChannel();
            GameHandler.channelBlue = event.getOption("blau").getAsChannel().asTextChannel();
            GameHandler.channelMagenta = event.getOption("magenta").getAsChannel().asTextChannel();
            GameHandler.channelGreen = event.getOption("grün").getAsChannel().asTextChannel();
            GameHandler.sendTeamSelectionMessage(event);
        }
        if (command.equals("answer")) {
            if (GameHandler.getGame() == null) {
                event.reply("Es Läuft gerade kein Spiel").setEphemeral(true).queue();
            } else {
                event.reply("Ihr habt die Antwort " + event.getOption("antwort").getAsString()
                        + " abgegeben").queue();
                String teamName = event.getMember().getRoles().get(0).getName();
                GameHandler.getGame().nextQuestion(event.getChannel().asTextChannel(), teamName);
                GameHandler.getGame().gameLeaderChannel.sendMessage("Team: " + teamName + " \"Kosten auf der Karte sind " + event.getOption("antwort").getAsString() + "\"").queue();
            }
        }
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        OptionData channelRed = new OptionData(OptionType.CHANNEL, "rot", "Channel", true);
        OptionData channelBlue = new OptionData(OptionType.CHANNEL, "blau", "Channel", true);
        OptionData channelMagenta = new OptionData(OptionType.CHANNEL, "magenta", "Channel", true);
        OptionData channelGreen = new OptionData(OptionType.CHANNEL, "grün", "Channel", true);
        commandData.add(Commands.slash("start", "Start the Quiz Game").addOptions(channelRed,channelBlue,channelMagenta,channelGreen));
        OptionData option1 = new OptionData(OptionType.STRING, "antwort", "Euer Ergebnis", true);
        commandData.add(Commands.slash("answer", "Antwort im Spiel Abgeben").addOptions(option1));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
