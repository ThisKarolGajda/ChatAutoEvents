package io.github.thisopkarol.autochatevents.autoEvents;

import io.github.thisopkarol.autochatevents.AutoChatEvents;
import io.github.thisopkarol.autochatevents.utils.ConfigUtils;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager {

    public static boolean isActiveEvents(){
        return RewriteText.hasEventStarted || WriteText.hasEventStarted;
    }

    public static String getActiveEvent(){
        if(RewriteText.hasEventStarted){
            return RewriteText.getEventName();
        } else if (WriteText.hasEventStarted){
            return WriteText.getEventName();
        }
        return "no";
    }

    public static String getEvents(){
        List<String> list = new ArrayList<>();
        String rewriteEvent = RewriteText.getEventName();
        String writeEvent = WriteText.getEventName();

        list.add(writeEvent);
        list.add(rewriteEvent);

        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static void startRandomEvent(){
        if(!isActiveEvents()){
            String event = getEvents();

            assert event != null;
            if (event.equals(ConfigUtils.getString("AutoEvents.RewriteText.name"))) {
                int min2 = 0;
                int max2 = 30;
                int random_int2 = (int)Math.floor(Math.random()*(max2-min2+1)+min2);
                RewriteText.sayRewriteTextEvent(random_int2*20);
            } else if (event.equals(ConfigUtils.getString("AutoEvents.WriteText.name"))){
                int min2 = 0;
                int max2 = 30;
                int random_int2 = (int)Math.floor(Math.random()*(max2-min2+1)+min2);
                WriteText.sayWriteTextEvent(random_int2*20);
            }
        }
    }

    public static void cancelEvents(){
        WriteText.hasEventStarted = false;
        RewriteText.hasEventStarted = false;
    }

    public static void randomEvent(){
        cancelEvents();
        int min2 = ConfigUtils.getInt("AutoEvents.RandomEvent.every.min")*20*60;
        int max2 = ConfigUtils.getInt("AutoEvents.RandomEvent.every.max")*20*60;
        final int[] random_int = {(int) Math.floor(Math.random() * (max2 - min2 + 1) + min2)};

        BukkitTask task = new BukkitRunnable(){
            @Override
            public void run() {
                startRandomEvent();
                random_int[0] = (int)Math.floor(Math.random()*(max2-min2+1)+min2);
            }
        }.runTaskTimer(AutoChatEvents.getInstance(), random_int[0], random_int[0]);

    }
}
