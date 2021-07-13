package io.github.thisopkarol.autochatevents.autoEvents;

import io.github.thisopkarol.autochatevents.AutoChatEvents;
import io.github.thisopkarol.autochatevents.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class RewriteText {

    public static boolean hasEventStarted = false;
    public static String message = "";

    public static void sayRewriteTextEvent(int timeToStart){
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
        Bukkit.broadcastMessage(ConfigUtils.getString("message.RewriteText").replace("%word%", sortString()));

    }

    public static String getEventName(){
        return ConfigUtils.getString("AutoEvents.RewriteText.name");
    }

    public static String[] getStrings(){
        String text = ConfigUtils.getString("AutoEvents.RewriteText.words");
        return text.split(";");
    }

    public static String getString(){
        return message;
    }

    public static void setString(){
        int min = 0;
        int max = getStrings().length;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        int i = 1;
        for(String string : getStrings()){
            if(i == random_int){
                message = string;
                return;
            } else {
                i++;
            }
        }
    }

    public static String sortString(){
        char[] chars = getString().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void setWinner(Player winner){
        hasEventStarted = false;
        Bukkit.broadcastMessage(ConfigUtils.getString("message.WinnerMessage").replace("%player%", winner.getName()));
        int min = ConfigUtils.getInt("AutoEvents.RewriteText.reward.min");
        int max = ConfigUtils.getInt("AutoEvents.RewriteText.reward.max");
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
