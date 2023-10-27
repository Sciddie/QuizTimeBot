package de.sciddie.quiztimebot.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

public class ButtonListenerGame extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

    }

    public static void removeButtons(Message message) {
        message.editMessage(MessageEditData.fromMessage(message)).setComponents().queue();
    }
}
