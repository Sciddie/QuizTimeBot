package de.sciddie.quiztimebot.listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        event.getChannel().getId();
    }


}
