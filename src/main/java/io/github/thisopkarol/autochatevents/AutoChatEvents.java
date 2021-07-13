package io.github.thisopkarol.autochatevents;

import io.github.thisopkarol.autochatevents.autoEvents.Manager;
import io.github.thisopkarol.autochatevents.commands.StartCommand;
import io.github.thisopkarol.autochatevents.events.PlayerChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoChatEvents extends JavaPlugin {

    private static AutoChatEvents autoChatEvents;

    @Override
    public void onEnable() {
        autoChatEvents = this;
        Bukkit.getPluginManager().registerEvents(new PlayerChatEvent(this), this);
        Bukkit.getPluginCommand("event").setExecutor(new StartCommand(this));
        saveDefaultConfig();
        Manager.randomEvent();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AutoChatEvents getInstance(){ return AutoChatEvents.autoChatEvents;}
}
