/*
 * Copyright (C) 2014 Weilon
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the Creative Commons Attribution-ShareAlike 4.0
 * International as published by the Creative Commons Organisation.
 *
 * You are free to copy, redistribute and create derivative works
 * from the material under the condition appropriate credit is
 * given to the creator. In addition, if you remix, transform or
 * build upon the material, you must distribute your contributions under
 * the same license as the original.
 *
 * You may get a copy of the license here: https://creativecommons.org/licenses/by-sa/4.0/
 */

package io.github.whalesaredelicious.simplethirst;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleThirst extends JavaPlugin {
    String version = "0.1";
    
    @Override
    public void onEnable() {
        getLogger().info("SimpleThirst version " + version + " has been enabled.");
    }
    
    @Override
    public void onDisable() {
    
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("thirst")) {
            //check thirst
            return true;
        }
        else {
            return false;
        }
    }
    
}
