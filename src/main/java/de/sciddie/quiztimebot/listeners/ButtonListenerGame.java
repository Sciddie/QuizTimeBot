package de.sciddie.quiztimebot.listeners;

import de.sciddie.quiztimebot.game.GameHandler;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

public class ButtonListenerGame extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getButton().getId().equals("next")) {
            String teamName = event.getMember().getRoles().get(0).getName();
            GameHandler.getGame().nextQuestion(event.getChannel().asTextChannel(), teamName);
            removeButtons(event.getMessage());
        }
        else if (event.getButton().getId().equals("right")) {
            String teamName = event.getMember().getRoles().get(0).getName();
            GameHandler.getGame().addPoint(teamName);
            GameHandler.getGame().nextQuestion(event.getChannel().asTextChannel(), teamName);
            removeButtons(event.getMessage());
        }
    }

    public static void removeButtons(Message message) {
        message.editMessage(MessageEditData.fromMessage(message)).setComponents().queue();
    }
}
