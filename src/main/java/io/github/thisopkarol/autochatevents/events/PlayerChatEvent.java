package io.github.thisopkarol.autochatevents.events;

import io.github.thisopkarol.autochatevents.AutoChatEvents;
import io.github.thisopkarol.autochatevents.autoEvents.Manager;
import io.github.thisopkarol.autochatevents.autoEvents.RewriteText;
import io.github.thisopkarol.autochatevents.autoEvents.WriteText;
import io.github.thisopkarol.autochatevents.utils.ConfigUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static io.github.thisopkarol.autochatevents.autoEvents.RewriteText.getString;

public class PlayerChatEvent implements Listener {

    public PlayerChatEvent(AutoChatEvents autoChatEvents){
    }

    @EventHandler
    public void playerChat(org.bukkit.event.player.PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (Manager.isActiveEvents()) {
            String eventName = Manager.getActiveEvent();
            if (eventName.equals(ConfigUtils.getString("AutoEvents.RewriteText.name"))) {

                if(message.toLowerCase().contains(getString().toLowerCase())){

                    RewriteText.setWinner(player);

                }
            } else if (eventName.equals(ConfigUtils.getString("AutoEvents.WriteText.name"))) {

                if(message.toLowerCase().contains(WriteText.getString().toLowerCase())){

                    WriteText.setWinner(player);

                }
            }
        }

    }
}
