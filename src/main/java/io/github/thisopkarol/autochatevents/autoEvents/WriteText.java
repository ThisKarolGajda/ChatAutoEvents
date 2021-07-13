package io.github.thisopkarol.autochatevents.autoEvents;

import io.github.thisopkarol.autochatevents.AutoChatEvents;
import io.github.thisopkarol.autochatevents.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;


public class WriteText {

    public static boolean hasEventStarted = false;
    public static String text = "";

    public static void sayWriteTextEvent(int timeToStart){
        hasEventStarted = true;
        Bukkit.broadcastMessage(ConfigUtils.getString("message.EventStarting").replace("%name%", getEventName()));

        //Po całym czasie
        new BukkitRunnable(){
            @Override
            public void run() {
                setHasEventStarted();
            }
        }.runTaskLater(AutoChatEvents.getInstance(), timeToStart);
    }

    public static void setHasEventStarted() {
        if(!hasEventStarted) return;
        setString();
        Bukkit.broadcastMessage(ConfigUtils.getString("message.EventStarted").replace("%name%", getEventName()));
        Bukkit.broadcastMessage(ConfigUtils.getString("message.WriteText").replace("%word%", sortString()));

    }

    public static String getEventName(){
        return ConfigUtils.getString("AutoEvents.WriteText.name");
    }

    public static String[] getStrings(){
        String text = ConfigUtils.getString("AutoEvents.WriteText.words");
        return text.split(";");
    }

    public static String getString(){
        return text;
    }

    public static void setString(){
        int min = 0;
        int max = getStrings().length;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        int i = 1;
        for(String string : getStrings()){
            if(i == random_int){
                text = string;
                return;
            } else {
                i++;
            }
        }
    }

    public static String sortString(){
        return getString();
    }

    public static void setWinner(Player winner){
        hasEventStarted = false;
        Bukkit.broadcastMessage(ConfigUtils.getString("message.WinnerMessage").replace("%player%", winner.getName()));
        int min = ConfigUtils.getInt("AutoEvents.WriteText.reward.min");
        int max = ConfigUtils.getInt("AutoEvents.WriteText.reward.max");
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        String command = "eco give %player% %random_int%".replace("%winner%", winner.getName()).replace("%random_int%", String.valueOf(random_int));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
        new BukkitRunnable(){
            @Override
            public void run() {
                winner.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 1));
            }
        }.run();
    }

}
