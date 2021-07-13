package io.github.thisopkarol.autochatevents.commands;

import io.github.thisopkarol.autochatevents.AutoChatEvents;
import io.github.thisopkarol.autochatevents.autoEvents.Manager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    public StartCommand(AutoChatEvents chatEvents){}


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("")|| sender.isOp()){
            if (args.length != 0) {
                if (args[0].equalsIgnoreCase("start")) {
                    if (!Manager.isActiveEvents()) {
                        Manager.startRandomEvent();
                    }
                }
                if (args[0].equalsIgnoreCase("stop")) {
                    if (Manager.isActiveEvents()) {
                        Manager.cancelEvents();
                    }
                }
            } else {
                sender.sendMessage("/event start/stop");
                return false;
            }
        }
        return false;
    }
}
