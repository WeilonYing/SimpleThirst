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

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public final class SimpleThirst
  extends JavaPlugin
  implements Listener
{
  Logger logger = Logger.getLogger("Minecraft");
  int thirstlevels = SimpleThirst.this.getConfig().getInt("number-of-thirst-levels"); //Change the value in the config file.
  
  @EventHandler
  public void onConsume(PlayerItemConsumeEvent event)
  {
    if (event.getPlayer().getItemInHand().getType().equals(Material.POTION))
    {
      int integerValue = getConfig().getInt(event.getPlayer().getName()) + getConfig().getInt("replenish-water");
      
      getConfig().set(event.getPlayer().getName(), Integer.valueOf(integerValue));
      saveConfig();
      event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "You drank water and gained " + getConfig().getInt("replenish-water") + " thirst levels");
      if (getConfig().getInt(event.getPlayer().getName()) > thirstlevels)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(thirstlevels));
        saveConfig();
      }
      else if (getConfig().getInt(event.getPlayer().getName()) < 0)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(thirstlevels));
        saveConfig();
      }
    }
    else if (event.getPlayer().getItemInHand().getType().equals(Material.MILK_BUCKET))
    {
      int integerValue1 = getConfig().getInt(event.getPlayer().getName()) + getConfig().getInt("replenish-milk");
      
      getConfig().set(event.getPlayer().getName(), Integer.valueOf(integerValue1));
      saveConfig();
      event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "You drank milk and gained " + getConfig().getInt("replenish-milk") + " thirst levels");
      if (getConfig().getInt(event.getPlayer().getName()) > thirstlevels)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(thirstlevels));
        saveConfig();
      }
      else if (getConfig().getInt(event.getPlayer().getName()) < 0)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(thirstlevels));
        saveConfig();
      }
    }
    else if (event.getPlayer().getItemInHand().getType().equals(Material.MELON))
    {
      int integerValue2 = getConfig().getInt(event.getPlayer().getName()) + getConfig().getInt("replenish-melon");
      

      getConfig().set(event.getPlayer().getName(), Integer.valueOf(integerValue2));
      saveConfig();
      event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "You ate a melon and gained " + getConfig().getInt("replenish-melon") + " thirst levels");
      if (getConfig().getInt(event.getPlayer().getName()) > thirstlevels)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(thirstlevels));
        saveConfig();
      }
      else if (getConfig().getInt(event.getPlayer().getName()) < 0)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(thirstlevels));
        saveConfig();
      }
    }
    else if (event.getPlayer().getItemInHand().getType().equals(Material.MUSHROOM_SOUP))
    {
      int integerValue2 = getConfig().getInt(event.getPlayer().getName()) + getConfig().getInt("replenish-mushroom-soup");
      

      getConfig().set(event.getPlayer().getName(), Integer.valueOf(integerValue2));
      saveConfig();
      event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "You drank a mushroom soup and gained " + getConfig().getInt("replenish-mushroom-soup") + " thirst levels");
      if (getConfig().getInt(event.getPlayer().getName()) > thirstlevels)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(thirstlevels));
        saveConfig();
      }
      else if (getConfig().getInt(event.getPlayer().getName()) < 0)
      {
        getConfig().set(event.getPlayer().getName(), Integer.valueOf(thirstlevels));
        saveConfig();
      }
    }
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
    
    Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));
    
    Score score = objective.getScore(event.getPlayer());
    score.setScore(getConfig().getInt(event.getPlayer().getName()));
    event.getPlayer().getPlayer().setScoreboard(board);
    event.getPlayer().setScoreboard(board);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    Player player = (Player)sender;
    if (commandLabel.equalsIgnoreCase("thirst")) {
        if (!(sender instanceof Player)) {}
        else {
            if (args.length == 0)
            {
              if (!getConfig().contains(player.getName()))
              {
                getConfig().set(player.getName(), Integer.valueOf(thirstlevels));
                saveConfig();
              }
              if (getConfig().getInt(sender.getName()) > thirstlevels)
              {
                getConfig().set(sender.getName(), Integer.valueOf(thirstlevels));
                saveConfig();
              }
              else if (getConfig().getInt(sender.getName()) > 6)
              {
                player.sendMessage(ChatColor.GREEN + "Your thirst level is " + 
                  getConfig().getString(player.getName()) + "/" + thirstlevels);
              }
              else if (getConfig().getInt(sender.getName()) > 4)
              {
                player.sendMessage(ChatColor.GOLD + "Your thirst level is " + 
                  getConfig().getString(player.getName()) + "/" + thirstlevels);
              }
              else if (getConfig().getInt(sender.getName()) > 0)
              {
                player.sendMessage(ChatColor.RED + "Your thirst level is " + 
                  getConfig().getString(player.getName()) + "/" + thirstlevels);
              }
              else if (getConfig().getInt(sender.getName()) < 0)
              {
                getConfig().set(sender.getName(), Integer.valueOf(thirstlevels));
                saveConfig();
                player.sendMessage(ChatColor.GREEN + "Your thirst level is " + 
                  getConfig().getString(player.getName()) + "/" + thirstlevels);
              }
            }

            else if (args.length == 3)
            {
              if (sender.hasPermission("hydrate.admin"))
                {
                    if (args[0].equalsIgnoreCase("set"))
                    {
                        int lol = Integer.parseInt(args[2]);

                        Player targetPlayer = player.getServer().getPlayer(args[1]);
                        if (lol > thirstlevels)
                        {
                            getConfig().set(targetPlayer.getName(), Integer.valueOf(thirstlevels));
                            saveConfig();
                        }
                        else
                        {
                            getConfig().set(targetPlayer.getName(), Integer.valueOf(lol));
                            saveConfig();
                        }
                        ScoreboardManager manager = Bukkit.getScoreboardManager();
                        Scoreboard board = manager.getNewScoreboard();

                        Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
                        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                        objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));

                        Score score = objective.getScore(targetPlayer.getPlayer());
                        score.setScore(getConfig().getInt(targetPlayer.getPlayer().getName()));
                        sender.sendMessage(targetPlayer.getName() + " thirst level has been set to: " + args[2] + "/" + thirstlevels);
                        targetPlayer.getPlayer().setScoreboard(board);
                        if (!targetPlayer.getPlayer().hasPermission("hydrate.nothirst")) {
                            targetPlayer.getPlayer().setScoreboard(board);
                        }
                        targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "Your thirst level has been set to: " + getConfig().getInt(targetPlayer.getPlayer().getName()) + "/" + thirstlevels);
                    }
                    else if (sender.hasPermission("hydrate.admin"))
                    {
                      sender.sendMessage(ChatColor.RED + "Usage: /thirst or /thirst set <player> <level>");
                    }
                    else
                    {
                      sender.sendMessage(ChatColor.RED + "Usage: /thirst");
                    }
                }
                else {
                  sender.sendMessage(ChatColor.RED + "You don't have permission.");
                }
            }
            else
            {
              sender.sendMessage(ChatColor.RED + "Usage: /thirst or /thirst set <player> <level>");
            }
        }
    }
    return false;
  }
  
  @EventHandler
  public void normalLogin(PlayerJoinEvent event)
  {
    if (1 > getConfig().getInt(event.getPlayer().getName()))
    {
      getConfig().set(event.getPlayer().getName(), Integer.valueOf(thirstlevels));
      saveConfig();
    }
    if (!getConfig().contains(event.getPlayer().getName()))
    {
      getConfig().set(event.getPlayer().getName(), Integer.valueOf(thirstlevels));
      saveConfig();
    }
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
    
    Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));
    

    Score score = objective.getScore(event.getPlayer());
    score.setScore(getConfig().getInt(event.getPlayer().getName()));
    if (!event.getPlayer().hasPermission("hydrate.nothirst")) {
      event.getPlayer().setScoreboard(board);
    }
  }
  
  @EventHandler
  public void onChange(PlayerChangedWorldEvent event)
  {
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    event.getPlayer().setScoreboard(manager.getNewScoreboard());
    

    Player p = event.getPlayer();
    



    boolean result1 = false;
    boolean result2 = false;
    boolean result3 = false;
    boolean result4 = false;
    boolean result5 = false;
    boolean result6 = false;
    boolean result7 = false;
    boolean result8 = false;
    boolean result9 = false;
    boolean result10 = false;
    if (getConfig().getBoolean("multiple-world-support"))
    {
      if ((getConfig().contains("multi-world1")) && 
        (getConfig().getString("multi-world1").contains(p.getWorld().getName())) && (getConfig().getString("multi-world1").length() == p.getWorld().getName().length())) {
        result1 = true;
      }
      if ((getConfig().contains("multi-world2")) && 
        (getConfig().getString("multi-world2").contains(p.getWorld().getName())) && (getConfig().getString("multi-world2").length() == p.getWorld().getName().length())) {
        result2 = true;
      }
      if ((getConfig().contains("multi-world3")) && 
        (getConfig().getString("multi-world3").contains(p.getWorld().getName())) && (getConfig().getString("multi-world3").length() == p.getWorld().getName().length())) {
        result3 = true;
      }
      if ((getConfig().contains("multi-world4")) && 
        (getConfig().getString("multi-world4").contains(p.getWorld().getName())) && (getConfig().getString("multi-world4").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world5")) && 
        (getConfig().getString("multi-world5").contains(p.getWorld().getName())) && (getConfig().getString("multi-world5").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world6")) && 
        (getConfig().getString("multi-world6").contains(p.getWorld().getName())) && (getConfig().getString("multi-world6").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world7")) && 
        (getConfig().getString("multi-world7").contains(p.getWorld().getName())) && (getConfig().getString("multi-world7").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world8")) && 
        (getConfig().getString("multi-world8").contains(p.getWorld().getName())) && (getConfig().getString("multi-world8").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world9")) && 
        (getConfig().getString("multi-world9").contains(p.getWorld().getName())) && (getConfig().getString("multi-world9").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
      if ((getConfig().contains("multi-world10")) && 
        (getConfig().getString("multi-world10").contains(p.getWorld().getName())) && (getConfig().getString("multi-world10").length() == p.getWorld().getName().length())) {
        result4 = true;
      }
    }
    if ((result1) || (result2) || (result3) || (result4) || (result5) || (result6) || (result7) || (result8) || (result9) || (result10) || (!getConfig().getBoolean("multiple-world-support")))
    {
      Scoreboard board = manager.getNewScoreboard();
      
      Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
      objective.setDisplaySlot(DisplaySlot.SIDEBAR);
      objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));
      

      Score score = objective.getScore(p.getPlayer());
      score.setScore(getConfig().getInt(p.getPlayer().getName()));
      if (!p.getPlayer().hasPermission("hydrate.nothirst")) {
        p.getPlayer().setScoreboard(board);
      }
    }
  }
  
  public void onEnable()
  {
    getLogger().info("SimpleThirst");
    getServer().getPluginManager().registerEvents(this, this);
    for (Player p : Bukkit.getOnlinePlayers())
    {
      if (!getConfig().contains(p.getName()))
      {
        getConfig().set(p.getName(), Integer.valueOf(thirstlevels));
        saveConfig();
      }
      ScoreboardManager manager = Bukkit.getScoreboardManager();
      Scoreboard board = manager.getNewScoreboard();
      
      Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
      objective.setDisplaySlot(DisplaySlot.SIDEBAR);
      objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));
      

      Score score = objective.getScore(p.getPlayer());
      score.setScore(getConfig().getInt(p.getPlayer().getName()));
      if (!p.getPlayer().hasPermission("hydrate.nothirst")) {
        p.getPlayer().setScoreboard(board);
      }
    }
    FileConfiguration config = getConfig();
    
    config.addDefault("time-in-seconds-to-remove-1", "180"); //integer
    config.addDefault("number-of-thirst-levels", "20"); //integer
    config.addDefault("scoreboard-text", "Thirst"); //string
    config.addDefault("ops-get-thirsty", "false"); //boolean
    config.addDefault("replenish-water", "12"); //integer
    config.addDefault("replenish-milk", "12"); //integer
    config.addDefault("replenish-melon", "4"); //integer
    config.addDefault("replenish-mushroom-soup", "9"); //integer
    config.addDefault("multiple-world-support", "false"); //boolean
    config.addDefault("multi-world1", "world"); //string
    config.addDefault("multi-world2", "world_nether"); //string
    config.addDefault("multi-world3", "world_the_end"); //string
    config.addDefault("death-message", "YOU HAVE DIED FROM DEHYDRATION!"); //string
    config.addDefault("use-thirst-messages", "true"); //boolean
    config.addDefault("thirst-warning-message", "You're starting to get a little thirsty. You might want to find a drink soon"); //string
    config.addDefault("thirst-warning-trigger-level", "10"); //integer
    config.addDefault("thirst-low-message", "Your throat is really dry. Find a drink, fast."); //string
    config.addDefault("thirst-low-trigger-level", "5"); //integer
    config.addDefault("thirst-danger-message", "You are seconds away from dying if you don't find a drink now."); //string
    config.addDefault("thirst-danger-trigger-level", "1"); //integer
    config.options()
      .header("#====== ONLY EDIT THE CONFIGURATION IN THIS FILE. NEVER EDIT USER DATA FROM HERE! ======\r\n======= THIS FILE STORES USER DATA ========");
    config.options().copyHeader(true);
    
    config.options().copyDefaults(true);
    saveConfig();
    
    Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable()
    {
      public void run()
      {
        Player[] playerlist = Bukkit.getOnlinePlayers();
        for (Player p : playerlist)
        {
          boolean result1 = false;
          boolean result2 = false;
          boolean result3 = false;
          boolean result4 = false;
          boolean result5 = false;
          boolean result6 = false;
          boolean result7 = false;
          boolean result8 = false;
          boolean result9 = false;
          boolean result10 = false;
          if (SimpleThirst.this.getConfig().getBoolean("multiple-world-support"))
          {
            if ((SimpleThirst.this.getConfig().contains("multi-world1")) && 
              (SimpleThirst.this.getConfig().getString("multi-world1").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world1").length() == p.getWorld().getName().length())) {
              result1 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world2")) && 
              (SimpleThirst.this.getConfig().getString("multi-world2").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world2").length() == p.getWorld().getName().length())) {
              result2 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world3")) && 
              (SimpleThirst.this.getConfig().getString("multi-world3").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world3").length() == p.getWorld().getName().length())) {
              result3 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world4")) && 
              (SimpleThirst.this.getConfig().getString("multi-world4").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world4").length() == p.getWorld().getName().length())) {
              result4 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world5")) && 
              (SimpleThirst.this.getConfig().getString("multi-world5").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world5").length() == p.getWorld().getName().length())) {
              result5 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world6")) && 
              (SimpleThirst.this.getConfig().getString("multi-world6").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world6").length() == p.getWorld().getName().length())) {
              result6 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world7")) && 
              (SimpleThirst.this.getConfig().getString("multi-world7").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world7").length() == p.getWorld().getName().length())) {
              result7 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world8")) && 
              (SimpleThirst.this.getConfig().getString("multi-world8").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world8").length() == p.getWorld().getName().length())) {
              result8 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world9")) && 
              (SimpleThirst.this.getConfig().getString("multi-world9").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world9").length() == p.getWorld().getName().length())) {
              result9 = true;
            }
            if ((SimpleThirst.this.getConfig().contains("multi-world10")) && 
              (SimpleThirst.this.getConfig().getString("multi-world10").contains(p.getWorld().getName())) && (SimpleThirst.this.getConfig().getString("multi-world10").length() == p.getWorld().getName().length())) {
              result10 = true;
            }
          }
          if ((result1) || (result2) || (result3) || (result4) || (result5) || (result6) || (result7) || (result8) || (result9) || (result10) || (!SimpleThirst.this.getConfig().getBoolean("multiple-world-support"))) {
            if (!p.getPlayer().hasPermission("hydrate.nothirst")) {
              if ((!p.getPlayer().isOp()) || (SimpleThirst.this.getConfig().getBoolean("ops-get-thirsty")))
              {
                if (!SimpleThirst.this.getConfig().contains(p.getName()))
                {
                  SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(thirstlevels));
                  SimpleThirst.this.saveConfig();
                }
                int integerValue = SimpleThirst.this.getConfig().getInt(p.getName()) - 1;
                
                SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(integerValue));
                SimpleThirst.this.saveConfig();
                if (SimpleThirst.this.getConfig().getInt(p.getName()) > thirstlevels)
                {
                  SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(thirstlevels));
                  SimpleThirst.this.saveConfig();
                }
                else if (SimpleThirst.this.getConfig().getInt(p.getName()) < 0)
                {
                  SimpleThirst.this.getConfig().set(p.getName(), Integer.valueOf(thirstlevels));
                  SimpleThirst.this.saveConfig();
                }
                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard board = manager.getNewScoreboard();
                
                Objective objective = board.registerNewObjective(ChatColor.AQUA + SimpleThirst.this.getConfig().getString("scoreboard-text"), "dummy");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                objective.setDisplayName(ChatColor.AQUA + SimpleThirst.this.getConfig().getString("scoreboard-text"));
                

                Score score = objective.getScore(p.getPlayer());
                score.setScore(SimpleThirst.this.getConfig().getInt(p.getPlayer().getName()));
                if (!p.getPlayer().hasPermission("hydrate.nothirst")) {
                  p.getPlayer().setScoreboard(board);
                }
                int playerThirstLevel = SimpleThirst.this.getConfig().getInt(p.getName());
                if (playerThirstLevel != thirstlevels) {
                  if (playerThirstLevel > 0) {
                    p.sendMessage(ChatColor.YELLOW + "Your thirst level has decreased to: " + playerThirstLevel + "/" + thirstlevels);
                  }
                  if (playerThirstLevel == 0) {
                    p.setHealth(0.0D);
                    p.sendMessage(ChatColor.RED + "Your thirst level has decreased to: " + playerThirstLevel + "/" + thirstlevels);
                  }
                  if (SimpleThirst.this.getConfig().getBoolean("use-thirst-messages")) {
                    if (playerThirstLevel == 0) {
                      p.sendMessage(ChatColor.RED + SimpleThirst.this.getConfig().getString("death-message"));
                    }
                    if (playerThirstLevel == SimpleThirst.this.getConfig().getInt("thirst-warning-trigger-level")) {
                        p.sendMessage(ChatColor.YELLOW + SimpleThirst.this.getConfig().getString("thirst-warning-message"));
                    }
                    if (playerThirstLevel == SimpleThirst.this.getConfig().getInt("thirst-low-trigger-level")) {
                        p.sendMessage(ChatColor.GOLD + SimpleThirst.this.getConfig().getString("thirst-low-message"));
                    }
                    if (playerThirstLevel == SimpleThirst.this.getConfig().getInt("thirst-danger-trigger-level")) {
                        p.sendMessage(ChatColor.RED + SimpleThirst.this.getConfig().getString("thirst-danger-message"));
                    }
                  }
                  /* DEPRECATED CODE IN CASE NEW CODE GOES WRONG.
                  if (SimpleThirst.this.getConfig().getInt(p.getName()) == 9)
                  {
                    p.sendMessage(ChatColor.GREEN + 
                      "=============SimpleThirst=============");
                    p.sendMessage(ChatColor.GREEN + 
                      SimpleThirst.this.getConfig().getString("level9-message") + " 9/" + thirstlevels);
                    p.sendMessage(ChatColor.GREEN + 
                      "================================");
                  }
                  else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 8)
                  {
                    p.sendMessage(ChatColor.GREEN + 
                      "=============SimpleThirst=============");
                    p.sendMessage(ChatColor.GREEN + 
                      SimpleThirst.this.getConfig().getString("level8-message") + " 8/" + thirstlevels);
                    p.sendMessage(ChatColor.GREEN + 
                      "================================");
                  }
                  else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 7)
                  {
                    p.sendMessage(ChatColor.GREEN + 
                      "=============SimpleThirst=============");
                    p.sendMessage(ChatColor.GREEN + 
                      SimpleThirst.this.getConfig().getString("level7-message") + " 7/" + thirstlevels);
                    p.sendMessage(ChatColor.GREEN + 
                      "================================");
                  }
                  else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 6)
                  {
                    p.sendMessage(ChatColor.GOLD + 
                      "===============SimpleThirst==============");
                    p.sendMessage(ChatColor.GOLD + 
                      SimpleThirst.this.getConfig().getString("level6-message") + " 6/" + thirstlevels);
                    p.sendMessage(ChatColor.GOLD + 
                      "===================================");
                  }
                  else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 5)
                  {
                    p.sendMessage(ChatColor.GOLD + 
                      "===============SimpleThirst================");
                    p.sendMessage(ChatColor.GOLD + 
                      SimpleThirst.this.getConfig().getString("level5-message") + " 5/" + thirstlevels);
                    p.sendMessage(ChatColor.GOLD + 
                      "=====================================");
                  }
                  else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 4)
                  {
                    p.sendMessage(ChatColor.RED + 
                      "============SimpleThirst============");
                    p.sendMessage(ChatColor.RED + 
                      SimpleThirst.this.getConfig().getString("level4-message") + " 4/" + thirstlevels);
                    p.sendMessage(ChatColor.RED + 
                      "==============================");
                  }
                  else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 3)
                  {
                    p.sendMessage(ChatColor.RED + 
                      "===============SimpleThirst================");
                    p.sendMessage(ChatColor.RED + 
                      SimpleThirst.this.getConfig().getString("level3-message") + " 3/" + thirstlevels);
                    p.sendMessage(ChatColor.RED + 
                      "=====================================");
                  }
                  else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 2)
                  {
                    PotionEffect nausea = new PotionEffect(
                      PotionEffectType.CONFUSION, 1000, 50);
                    p.addPotionEffect(nausea, true);
                    p.sendMessage(ChatColor.RED + 
                      "===============SimpleThirst================");
                    p.sendMessage(ChatColor.RED + 
                      SimpleThirst.this.getConfig().getString("level2-message") + " 2/" + thirstlevels);
                    p.sendMessage(ChatColor.RED + 
                      "=====================================");
                  }
                  else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 1)
                  {
                    PotionEffect nausea = new PotionEffect(
                      PotionEffectType.CONFUSION, 1000, 50);
                    p.addPotionEffect(nausea, true);
                    p.sendMessage(ChatColor.RED + 
                      "=================SimpleThirst==================");
                    p.sendMessage(ChatColor.RED + 
                      SimpleThirst.this.getConfig().getString("level1-message") + " 1/" + thirstlevels);
                    p.sendMessage(ChatColor.RED + 
                      "=========================================");
                  }
                  else if (SimpleThirst.this.getConfig().getInt(p.getName()) == 0)
                  {
                    p.setHealth(0.0D);
                    p.sendMessage(ChatColor.RED + 
                      "==============SimpleThirst==============");
                    p.sendMessage(ChatColor.RED + 
                      SimpleThirst.this.getConfig().getString("death-message"));
                    p.sendMessage(ChatColor.RED + 
                      "==================================");
                  }*/
                }
              }
            }
          }
        }
      }
    }, 20 * getConfig().getInt("time-in-seconds-to-remove-1"), 20 * getConfig().getInt("time-in-seconds-to-remove-1")); //See config
  }
  
  @EventHandler
  public void onRespawn(PlayerRespawnEvent event)
  {
    getConfig().set(event.getPlayer().getName(), Integer.valueOf(thirstlevels));
    saveConfig();
    
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
    
    Objective objective = board.registerNewObjective(ChatColor.AQUA + getConfig().getString("scoreboard-text"), "dummy");
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName(ChatColor.AQUA + getConfig().getString("scoreboard-text"));
    
    Score score = objective.getScore(event.getPlayer());
    score.setScore(getConfig().getInt(event.getPlayer().getName()));
    if (!event.getPlayer().hasPermission("hydrate.nothirst")) {
      event.getPlayer().setScoreboard(board);
    }
  }
}
