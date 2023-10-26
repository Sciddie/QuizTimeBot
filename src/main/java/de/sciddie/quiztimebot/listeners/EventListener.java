package de.sciddie.quiztimebot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.RestAction;

import java.awt.*;
import java.util.List;

import java.util.ArrayList;

public class EventListener extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getButton().getId().equals("start")) {

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
            List<Role> roles = event.getGuild().getRoles();
            List<String> rolenames = new ArrayList<>();
            for (Role i : roles) {
                rolenames.add(i.getName());
            }
            if (!rolenames.contains("Rot")) {
                event.getGuild().createRole()
                        .setName("Rot")
                        .setColor(Color.RED).complete();
            }
            if (!rolenames.contains("Blau")) {
                event.getGuild().createRole()
                        .setName("Blau")
                        .setColor(Color.BLUE).complete();
            }
            if (!rolenames.contains("Magenta")) {
                event.getGuild().createRole()
                        .setName("Magenta")
                        .setColor(Color.MAGENTA).complete();
            }
            if (!rolenames.contains("Grün")) {
                event.getGuild().createRole()
                        .setName("Grün")
                        .setColor(Color.GREEN).complete();
            }

            EmbedBuilder start = new EmbedBuilder();
            start.setTitle("Start Game");
            start.setDescription("Erst wenn jeder in einem Team ist!");
            event.replyEmbeds(start.build()).setEphemeral(true).addActionRow(Button.success("start", "Starten")).queue();

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Wilkommen bei QuizTime");
            eb.setDescription("Wähle dein Team!");
            event.getHook().sendMessageEmbeds(eb.build()).addActionRow(
                    Button.primary("blue", "Blau"),
                    Button.danger("red", "Rot"),
                    Button.secondary("magenta", "Magenta"),
                    Button.success("green", "Grün")
            ).queue();
        }
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("start", "Start the Quiz Game"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
