package io.github.thisopkarol.autochatevents.utils;

import io.github.thisopkarol.autochatevents.AutoChatEvents;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtils {

    public static FileConfiguration getConfig(){ return AutoChatEvents.getInstance().getConfig(); }

    public static String getString(String path){ return FormatUtils.formatText(getConfig().getString(path)); }

    public static int getInt(String path){ return getConfig().getInt(path); }

    public static boolean getBoolean(String path){ return getConfig().getBoolean(path); }

    public static String[] getStrings(String path){ return getConfig().getStringList(path).toArray(new String[0]); }


}
